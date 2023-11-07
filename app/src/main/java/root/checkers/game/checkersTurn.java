package root.checkers.game;

import root.common.Board;
import root.common.ChessPlayer;
import root.common.Interfaces.turn;
import root.common.Piece;
import root.common.Position;

import java.util.List;

public class checkersTurn implements turn {
    int dif;
    public checkersTurn(int dif){
        this.dif = dif;
    }
    @Override
    public List<ChessPlayer> nextTurn(List<ChessPlayer> chessPlayers, Board board, Position oldPos, Position newPos) {
        if (hasAnotherMove(board, oldPos, newPos) != board){
            return chessPlayers;
        }
        else {
            List<ChessPlayer> newList = chessPlayers;
            for (int i = 0; i <= chessPlayers.toArray().length - 1; i++) {
                if (chessPlayers.get(i).getTurn()) {
                    newList.set(i,chessPlayers.get(i).changeTurn());
                    chessPlayers.get(i).changeTurn();
                    if (i + 1 == chessPlayers.toArray().length) {
                        newList.set(0,chessPlayers.get(0).changeTurn());
                        break;
                    } else {
                        newList.set(i+1,chessPlayers.get(i + 1).changeTurn());
                        break;
                    }
                }
            }
            return newList;
        }
    }

    @Override
    public ChessPlayer getCurrent(List<ChessPlayer> chessPlayers) {

        return chessPlayers.stream().filter(ChessPlayer::getTurn).findFirst().get();
    }
    private Board hasAnotherMove (Board board, Position oldpos, Position newPos){
        Piece piece = newPos.getPiece();
        Board board1 = board.copy();
        int x = oldpos.getX() - newPos.getX();
        if (Math.abs(x)  < dif){
            return board;
        }
        Board newTablero;
        if (board1.getPosition(newPos.getX() + dif, newPos.getY() + dif) != null) {
            newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() + dif, newPos.getY() + dif), piece);
            if (newTablero != board1) {
                return newTablero;
            }
        }
        if (board1.getPosition(newPos.getX() - dif, newPos.getY() + dif) != null) {
            newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() - dif, newPos.getY() + dif), piece);
            if (newTablero != board1) {
                return newTablero;
            }
        }
        if (board1.getPosition(newPos.getX() + dif, newPos.getY() - dif) != null) {

            newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() + dif, newPos.getY() - dif), piece);
            if (newTablero != board1) {
                return newTablero;
            }
        }
        if (board1.getPosition(newPos.getX() - dif, newPos.getY() - dif) != null) {
            newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() - dif, newPos.getY() - dif), piece);
            if (newTablero != board1) {
                return newTablero;
            }
        }
        return board;
    }
}
