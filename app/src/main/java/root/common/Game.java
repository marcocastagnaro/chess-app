package root.common;
import root.chess.ChessTurn;
import root.common.Interfaces.*;

import java.util.List;
import java.util.Objects;

public class Game implements gameInterface {
    private Board board;
    private List<ChessPlayer> chessPlayers;
    private final GameVersion gameVersion;
    private final turn customizeTurn;

    public Game(Board board, List<ChessPlayer> chessPlayers, GameVersion gameVersion, ChessPlayer initialPlayer, turn customizeTurn) {
        this.board = board;
        this.chessPlayers = firstTurn(initialPlayer, chessPlayers);
        this.gameVersion = gameVersion;
        this.customizeTurn = customizeTurn;
    }

    public Game(Board board, List<ChessPlayer> chessPlayers, GameVersion gameVersion, turn customizeTurn) {
        this.board = board;
        this.chessPlayers = chessPlayers;
        this.gameVersion = gameVersion;
        this.customizeTurn = customizeTurn;
    }
    private List<ChessPlayer> firstTurn(ChessPlayer initial, List<ChessPlayer> chessPlayers){
        List<ChessPlayer> newList = chessPlayers;
        for (int i=0; i<chessPlayers.size(); i++){
            if (chessPlayers.get(i).getColor() == initial.getColor()){
                newList.set(i,new ChessPlayer(chessPlayers.get(i).getName(), chessPlayers.get(i).getColor(), true));
            }
        }
        return newList;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public Game move(Position oldPos, Position newPosition) {
        ChessPlayer current = customizeTurn.getCurrent(chessPlayers);
        Piece piece = oldPos.getPiece();
        if (validTurn(current, piece)) {
            Board tablero = current.movePiece( oldPos,newPosition, board);

            for (specialRules spec: gameVersion.getRules()){
                if (spec.validateMove(tablero,oldPos, newPosition) != tablero){
                    Board board1 = spec.validateMove(tablero, oldPos, newPosition);
                    return new Game(board1, changeTurn(this.chessPlayers, tablero, tablero.getPosition(oldPos.getX(), oldPos.getY()), tablero.getPosition(newPosition.getX(), newPosition.getY())), gameVersion, customizeTurn);
                }
            }
            if (tablero == board) return this;

            for (validators val: gameVersion.getValidators()){
                if (val.validateMove(this, tablero, current.getColor(), newPosition, oldPos) != this){
                    return this; //si encuentra un nuevo game (un nuevo movimiento) devuelvo el tablero de antes
                }
            }
            return new Game(tablero, changeTurn(this.chessPlayers, tablero,  tablero.getPosition(oldPos.getX(), oldPos.getY()), tablero.getPosition(newPosition.getX(), newPosition.getY())), gameVersion, customizeTurn);
        }
        return this;
    }
    private List<ChessPlayer> changeTurn (List <ChessPlayer> chessPlayer, Board board, Position oldPos, Position newPos){
        return customizeTurn.nextTurn(chessPlayer, board, oldPos, newPos);
    }


    private static boolean validTurn(ChessPlayer current, Piece piece) {
        return Objects.equals(piece.getColor(), current.getColor());
    }
    @Override
    public List<ChessPlayer> getChessPlayers() {
        return chessPlayers;
    }

    public List<Piece> getPiecies() {
        return board.getPiecies();
    }



    @Override
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board) {
        for (victoryValidator valid: gameVersion.getVictoryInt()){
            if (valid.validateVictory(chessPlayer,board)){
                return true;
            }
        }
        return false;
    }

    public GameVersion getGameVersion() {
        return gameVersion;
    }

    public turn getCustomizeTurn() {
        return customizeTurn;
    }
}
