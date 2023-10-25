package Checkers.game;

import Checkers.movements.obligatoryMovement;
import Checkers.movements.specialMovementVal;
import Classic.GameVersion;
import Classic.Board;
import Classic.ChessPlayer;
import Classic.Interfaces.movementValidator;
import Classic.Interfaces.specialMovementValidator;
import Classic.Piece;
import Classic.Position;
import java.util.List;
import java.util.Objects;

public class gameCheckers implements gameCheckersInterface {
    Board board;
    List<ChessPlayer> chessPlayers;
    GameVersion gameVersion;

    public gameCheckers(Board board, List<ChessPlayer> chessPlayers, GameVersion gameVersion, ChessPlayer initialPlayer) {
        this.board = board;
        this.chessPlayers = chessPlayers;
        this.gameVersion = gameVersion;
        initialPlayer.changeTurn();
    }

    public gameCheckers(Board board, List<ChessPlayer> chessPlayers, GameVersion gameVersion) {
        this.board = board;
        this.chessPlayers = chessPlayers;
        this.gameVersion = gameVersion;
    }

    @Override
    public List<Piece> getPiecies() {
        return board.getPiecies();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public gameCheckers move(Position oldPos, Position newPosition) {
        ChessPlayer current = getPlayerForCurrentTurn();
        Piece piece = oldPos.getPiece();
        if (Objects.equals(piece.getColor(), current.getColor())) {
//            if (validSpecialMov(newPosition, oldPos)) {
//                Board newboard = checkSpecialCond(oldPos, newPosition);
//                return new gameCheckers(newboard, chessPlayers, gameVersion);
//            }
            Board tablero = current.movePiece(piece, newPosition, board);
            if (tablero == board) {
                return this;
            }
//            if (obligatoryMovement.validateMovement(tablero)) {
//                return this;
//            }
            nextTurn();
            return new gameCheckers(tablero, chessPlayers, gameVersion);
        }
        return this;
    }
    private boolean validSpecialMov(Position newPosition, Position oldPos) {
        return proveSpecialMove( oldPos, newPosition) != this.getBoard();
    }

    public Board checkSpecialCond (Position oldPos, Position newPosition){
        Board newboard = proveSpecialMove( oldPos, newPosition);
        nextTurn();
        return newboard;
    }
    public Board proveSpecialMove(Position oldPos, Position newPos) {
        for (specialMovementValidator spec: gameVersion.getSpecialMovementValidators()) {
            Piece piece = oldPos.getPiece();
            if (piece.moveValidator(oldPos, newPos, board)) {
                    Board newBoard = spec.validateMove(board, oldPos, newPos);
                    if (newBoard != board) {
                        return newBoard;
                    }
                }
            }
        return board;
    }
    @Override
    public List<ChessPlayer> getChessPlayers() {
        return chessPlayers;
    }

    @Override
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board) {
        return gameVersion.getVictoryInt().validateVictory(chessPlayer, board);
    }

    public ChessPlayer getPlayerForCurrentTurn() {
        return chessPlayers.stream().filter(ChessPlayer::getTurn).findFirst().get();
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
}