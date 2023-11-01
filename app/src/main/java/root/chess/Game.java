package root.chess;
import root.common.*;
import root.common.Interfaces.specialMovementValidator;
import root.common.Interfaces.turn;
import root.common.victory.checkValidator;

import java.util.List;
import java.util.Objects;

public class Game implements root.chess.gameInterface {
    private final List<ChessPlayer> chessPlayers;
    Board board;
    GameVersion gameVersion;
    turn customizeTurn;

    public Game(List<ChessPlayer> chessPlayers, Board board, GameVersion gameVersion, ChessPlayer initialPlayer, turn customizeTurn) {
        this.chessPlayers = chessPlayers;
        this.board = board;
        this.gameVersion = gameVersion;
        this.customizeTurn = customizeTurn;
        initialPlayer.changeTurn();
    }
    public Game(List<ChessPlayer> chessPlayers, Board board, GameVersion gameVersion, turn customizeTurn) {
        this.chessPlayers = chessPlayers;
        this.board = board;
        this.gameVersion = gameVersion;
        this.customizeTurn = customizeTurn;
    }

    public Game move(Position newPosition, Position oldPos) {
        ChessPlayer current = customizeTurn.getCurrent(chessPlayers);
        Piece piece = oldPos.getPiece();
        if (Objects.equals(piece.getColor(), current.getColor())) {
            if (validSpecialMov(newPosition, oldPos)) {
                Board newboard = checkSpecialCond(oldPos,newPosition,current,piece);
                return new Game(chessPlayers, newboard, gameVersion, customizeTurn);
            }
            Board tablero = current.movePiece(piece, newPosition, board);
            if (tablero == board) {
                return this;
            }
            if (piece.isFirstMove()) {
                Piece piece1 = new Piece(piece.getName(), piece.getColor(), piece.getMovements(), piece.getId(), false);
                Position newPos = new Position(newPosition.getX(), newPosition.getY(), piece1);
                Board table = tablero.move(oldPos, newPos, piece1);
                return new Game(chessPlayers, table, gameVersion, customizeTurn);
            }
            if (gameVersion.hasCheckValidator()) {
                if (gameVersion.getCheckval().validateMove(tablero, current.getColor())) {
                    return this;
                }
            }

            customizeTurn.nextTurn(chessPlayers);
            return new Game(chessPlayers, tablero, gameVersion, customizeTurn);
        }
        return this;
    }

    private boolean validSpecialMov(Position newPosition, Position oldPos) {
        return proveSpecialMove(oldPos, newPosition) != this.getBoard();
    }

    public Board checkSpecialCond (Position oldPos, Position newPosition, ChessPlayer current, Piece piece){
            Board newboard = proveSpecialMove( oldPos, newPosition);
            if (gameVersion.getCheckval() != null) {
                if (gameVersion.getCheckval().validateMove(newboard, current.getColor())) {
                    return board;
                }
            }
            if (piece.isFirstMove()) {
                Piece piece1 = new Piece(piece.getName(), piece.getColor(), piece.getMovements(), piece.getId(), false);
                Position newPos = new Position(newPosition.getX(), newPosition.getY(), piece1);
                Board table = newboard.move(oldPos, newPos, piece1);
                return table;
            }
            customizeTurn.nextTurn(chessPlayers);
            return newboard;
    }
    @Override
    public List<Piece> getPiecies() {
        return board.getPiecies();
    }

    public Board getBoard() {
        return board;
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

    public Board proveSpecialMove(Position oldPos, Position newPos) {
        for (specialMovementValidator spec: gameVersion.getSpecialMovementValidators()) {
            Board newBoard = spec.validateMove(board, oldPos, newPos);
            if (newBoard!= board) {
                return newBoard;
            }
        }
        return board;
    }
    public GameVersion getGameVersion() {
        return gameVersion;
    }

    public turn getTurn() {
        return customizeTurn;
    }
}
