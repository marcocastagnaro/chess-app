package root.checkers.game;

import root.checkers.movements.specialMovementVal;
import root.common.GameVersion;
import root.common.Board;
import root.common.ChessPlayer;
import root.common.Interfaces.specialMovementValidator;
import root.common.Interfaces.turn;
import root.common.Piece;
import root.common.Position;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class gameCheckers implements gameCheckersinterface {
    private Board board;
    private List<ChessPlayer> chessPlayers;
    private GameVersion gameVersion;
    private turn customizeTurn;

    public gameCheckers(Board board, List<ChessPlayer> chessPlayers, GameVersion gameVersion, ChessPlayer initialPlayer, turn customizeTurn) {
        this.board = board;
        this.chessPlayers = chessPlayers;
        this.gameVersion = gameVersion;
        this.customizeTurn = customizeTurn;
        initialPlayer.changeTurn();
    }

    public gameCheckers(Board board, List<ChessPlayer> chessPlayers, GameVersion gameVersion, turn customizeTurn) {
        this.board = board;
        this.chessPlayers = chessPlayers;
        this.gameVersion = gameVersion;
        this.customizeTurn = customizeTurn;
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
        ChessPlayer current = customizeTurn.getCurrent(chessPlayers);
        Piece piece = oldPos.getPiece();
        Optional<gameCheckers> newboardOpt = movement(oldPos, newPosition, current, piece);

        return newboardOpt.orElse(this);

    }


    private Optional<gameCheckers> movement(Position oldPos, Position newPosition, ChessPlayer current, Piece piece) {
        if (validTurn(current, piece)) {
            Optional<gameCheckers> newboard = specialMovements(oldPos, newPosition);
            if (newboard.isPresent()) return newboard;

            Board tablero = current.movePiece( oldPos,newPosition, board);
            Optional<gameCheckers> x = differentBoard(tablero);
            if (x.isPresent()) return x;

            Optional<gameCheckers> tablero1 = obligatoryMove(oldPos, newPosition, current, tablero);
            if (tablero1.isPresent()) return tablero1;

            customizeTurn.nextTurn(chessPlayers);
            return Optional.of(new gameCheckers(tablero, chessPlayers, gameVersion, customizeTurn));
        }
        return Optional.empty();
    }

    private static boolean validTurn(ChessPlayer current, Piece piece) {
        return Objects.equals(piece.getColor(), current.getColor());
    }


    private Optional<gameCheckers> specialMovements(Position oldPos, Position newPosition) {
        if (validSpecialMov(newPosition, oldPos)) {
            Board newboard = makeSpecialMove(oldPos, newPosition);
            return Optional.of(new gameCheckers(newboard, chessPlayers, gameVersion, customizeTurn));
        }
        return Optional.empty();
    }


    private Optional<gameCheckers> differentBoard(Board tablero) {
        if (tablero == board) {
            return Optional.of(this);
        }
        return Optional.empty();
    }


    private Optional<gameCheckers> obligatoryMove(Position oldPos, Position newPosition, ChessPlayer current, Board tablero) {
        if (gameVersion.hasObligatory()) {
            if (gameVersion.getObligatory().hasAnotherMove(tablero, oldPos, tablero.getPosition(newPosition.getX(), newPosition.getY())) != tablero) {
                return Optional.of(new gameCheckers(tablero, chessPlayers, gameVersion, customizeTurn));
            }
            if (gameVersion.getObligatory().validateMove(tablero, current.getColor(), oldPos) != tablero) {
                return Optional.of(this);
            }
        }
        return Optional.empty();
    }


    private boolean validSpecialMov(Position newPosition, Position oldPos) {
        return proveSpecialMove( oldPos, newPosition) != this.getBoard();
    }

    public Board makeSpecialMove(Position oldPos, Position newPosition){
        Board newboard = proveSpecialMove( oldPos, newPosition);
        customizeTurn.nextTurn(chessPlayers);
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
        return newBoard != board;
    }

    @Override
    public List<ChessPlayer> getChessPlayers() {
        return chessPlayers;
    }

    @Override
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board) {
        return gameVersion.getVictoryInt().validateVictory(chessPlayer, board);
    }
}