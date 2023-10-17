package Chess;

import Interfaces.gameInterface;

import java.util.List;
import java.util.Objects;

public class Game implements gameInterface {
    private List <ChessPlayer> chessPlayers;
    Board board;
    GameVersion gameVersion;

    public Game (List<ChessPlayer> chessPlayers, Board board) {
        this.chessPlayers = chessPlayers;
        this.board = board;
    }

    public Game move (Position newPosition, Position oldPos){
        ChessPlayer current = getPlayerForCurrentTurn();
        Piece piece = oldPos.getPiece();
        if (Objects.equals(piece.getColor(), current.getColor())) {
            Board tablero = current.movePiece(piece, newPosition, board);
            if (tablero == board) {
                return this;
            }
            if (piece.isFirstMove()){
                piece.setFirstMove(false);
            }
            check(tablero);
            nextTurn();
            return new Game(chessPlayers, tablero);
        }
        return this;
    }

    @Override
    public List<Piece> getPiecies() {
        return board.getPiecies();
    }

    public Board getBoard() {
        return board;
    }


    private void nextTurn() {
        for (int i = 0; i <= chessPlayers.toArray().length - 1; i++){
            if (chessPlayers.get(i).getTurn()){
                chessPlayers.get(i).changeTurn();
                if ( i+1 == chessPlayers.toArray().length){
                    chessPlayers.get(0).changeTurn();
                    break;
                }
                else {
                    chessPlayers.get(i+1).changeTurn();
                    break;
                }
            }
        }
    }

    public ChessPlayer getPlayerForCurrentTurn() {
        return chessPlayers.stream().filter(ChessPlayer::getTurn).findFirst().get();
    }
    private void check (Board tablero) {
        for (ChessPlayer chessPlayer : chessPlayers) {
            chessPlayer.setCheckmate(isInCheckamte(chessPlayer));
            chessPlayer.setCheck(chessPlayer.isInCheck(tablero));
            }
    }

    public boolean isInCheckamte(ChessPlayer chessPlayer){
        for (int x = 0; x < board.getRow(); x++) {
            for (int y = 0; y < board.getColumn(); y++) {
                Position possiblePos = board.getPosition(x,y);
                //Primero chequiamos si el rey se puede mover a los espacios adyacentes
                if (board.findKing(chessPlayer.getColor()) != null) {
                    Board newBoard = chessPlayer.movePiece(board.findKing(chessPlayer.getColor()), possiblePos, board);
                    if (newBoard != board) {
                        return false;
                    }
                }
                else { //por ahora devolvemos false si no existe el king para no tener problemas en los tests
                    return false;
                }
            }
        }
        if (board.pieceInterceptsCheck(chessPlayer)) {
            return false;//Chequeamos si alguna pieza puede interceptar el jaque
        }
        return true;
    }

    public List<ChessPlayer> getChessPlayers() {
        return chessPlayers;
    }
}
