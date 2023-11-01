package root.checkers.game;

import root.checkers.movements.specialMovementVal;
import root.common.GameVersion;
import root.common.Board;
import root.common.ChessPlayer;
import root.common.Interfaces.specialMovementValidator;
import root.common.Piece;
import root.common.Position;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class gameCheckers implements root.checkers.game.gameCheckersInterface {
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
        gameCheckers newboard = movement(oldPos, newPosition, current, piece);
        if (newboard != null) return newboard;
        return this;
    }

    @Nullable
    private gameCheckers movement(Position oldPos, Position newPosition, ChessPlayer current, Piece piece) {
        if (Objects.equals(piece.getColor(), current.getColor())) {
            gameCheckers newboard = specialMovements(oldPos, newPosition);
            if (newboard != null) return newboard;
            Board tablero = current.movePiece(piece, newPosition, board);
            gameCheckers x = differentBoard(tablero);
            if (x != null) return x;
            gameCheckers tablero1 = obligatoryMove(oldPos, newPosition, current, tablero);
            if (tablero1 != null) return tablero1;
            nextTurn();
            return new gameCheckers(tablero, chessPlayers, gameVersion);
        }
        return null;
    }

    @Nullable
    private gameCheckers specialMovements(Position oldPos, Position newPosition) {
        if (validSpecialMov(newPosition, oldPos)) {
            Board newboard = checkSpecialCond(oldPos, newPosition);
            return new gameCheckers(newboard, chessPlayers, gameVersion);
        }
        return null;
    }

    @Nullable
    private gameCheckers differentBoard(Board tablero) {
        if (tablero == board) {
            return this;
        }
        return null;
    }

    @Nullable
    private gameCheckers obligatoryMove(Position oldPos, Position newPosition, ChessPlayer current, Board tablero) {
        if(gameVersion.hasObligatory()){
            if (gameVersion.getObligatory().hasAnotherMove(tablero, oldPos, tablero.getPosition(newPosition.getX(), newPosition.getY())) != tablero){
                return new gameCheckers(tablero, chessPlayers, gameVersion);
            }
            if (gameVersion.getObligatory().validateMove(tablero, current.getColor(), oldPos) != tablero) {
                return this;
            }
        }
        return null;
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
        specialMovementVal special = null;
        special = getSpecialMovementVal(special);
        for (specialMovementValidator spec: gameVersion.getSpecialMovementValidators()) {
            assert special != null;
            if (special.validateMove(board, oldPos, newPos) != board && spec != special) {
                    Board newBoard = spec.validateMove(board, oldPos, newPos);
                if (changes(newBoard)) return newBoard;
            }
        }
        return board;
    }

    private specialMovementVal getSpecialMovementVal(specialMovementVal special) {
        for (specialMovementValidator spec: gameVersion.getSpecialMovementValidators()) {
            if (spec instanceof specialMovementVal){
                special = (specialMovementVal) spec;
            }
        }
        return special;
    }

    private boolean changes(Board newBoard) {
        if (newBoard != board) {
            return true;
        }
        return false;
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