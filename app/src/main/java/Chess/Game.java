package Chess;

import Interfaces.gameInterface;
import Interfaces.specialMovementValidator;
import Interfaces.victoryValidator;
import javafx.geometry.Pos;
import victory.checkValidator;

import java.util.List;
import java.util.Objects;

public class Game implements gameInterface {
    private final List<ChessPlayer> chessPlayers;
    Board board;
    GameVersion gameVersion;

    public Game(List<ChessPlayer> chessPlayers, Board board, GameVersion gameVersion) {
        this.chessPlayers = chessPlayers;
        this.board = board;
        this.gameVersion = gameVersion;
    }

    public Game move(Position newPosition, Position oldPos) {
        ChessPlayer current = getPlayerForCurrentTurn();
        Piece piece = oldPos.getPiece();
        if (Objects.equals(piece.getColor(), current.getColor())) {
            if (validSpecialMov(newPosition, oldPos)) {
                return checkSpecialCond(oldPos, newPosition, current, piece);
            }
            Board tablero = current.movePiece(piece, newPosition, board);
            if (tablero == board) {
                return this;
            }
            if (piece.isFirstMove()) {
                piece.setFirstMove(false);
            }
            if (gameVersion.getCheckval() != null) {
                if (gameVersion.getCheckval().isInCheck(tablero, current.getColor())) {
                    return this;
                }

            }

            nextTurn();
            return new Game(chessPlayers, tablero, gameVersion);
        }
        return this;
    }

    private boolean validSpecialMov(Position newPosition, Position oldPos) {
        return proveSpecialMove(this, oldPos, newPosition).getBoard() != this.getBoard();
    }

    public Game checkSpecialCond (Position oldPos, Position newPosition, ChessPlayer current, Piece piece){
            Game newgame = proveSpecialMove(this, oldPos, newPosition);
            if (gameVersion.getCheckval() != null) {
                if (gameVersion.getCheckval().isInCheck(newgame.getBoard(), current.getColor())) {
                    return this;
                }
            }
            if (piece.isFirstMove()) {
                piece.setFirstMove(false);
            }
            nextTurn();
            return newgame;
    }
    @Override
    public List<Piece> getPiecies() {
        return board.getPiecies();
    }

    public Board getBoard() {
        return board;
    }


    private void nextTurn() {
        for (int i = 0; i <= chessPlayers.toArray().length - 1; i++) {
            if (chessPlayers.get(i).getTurn()) {
                chessPlayers.get(i).changeTurn();
                if (i + 1 == chessPlayers.toArray().length) {
                    chessPlayers.get(0).changeTurn();
                    break;
                } else {
                    chessPlayers.get(i + 1).changeTurn();
                    break;
                }
            }
        }
    }

    public ChessPlayer getPlayerForCurrentTurn() {
        return chessPlayers.stream().filter(ChessPlayer::getTurn).findFirst().get();
    }


    public List<ChessPlayer> getChessPlayers() {
        return chessPlayers;
    }

    @Override
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board) {
        return gameVersion.getVictoryInt().validateVictory(chessPlayer, board);
    }

    public checkValidator getCheckval() {
        return gameVersion.getCheckval();
    }

    public Game proveSpecialMove(Game game, Position oldPos, Position newPos) {
        for (specialMovementValidator spec: gameVersion.getSpecialMovementValidators()) {
            Game newgame = spec.validateMove(game, oldPos, newPos);
            if (newgame.getBoard() != game.getBoard()) {
                return newgame;
            }
        }
        return this;
    }
    public GameVersion getGameVersion() {
        return gameVersion;
    }

}
