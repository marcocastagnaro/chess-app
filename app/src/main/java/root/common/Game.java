package root.common;
import root.common.Interfaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game implements GameInterface {
    private Board board;
    private List<Player> players;
    private final GameVersion gameVersion;
    private final Turn customizeTurn;

    public Game(Board board, List<Player> players, GameVersion gameVersion, Player initialPlayer, Turn customizeTurn) {
        this.board = board;
        this.players = firstTurn(initialPlayer, players);
        this.gameVersion = gameVersion;
        this.customizeTurn = customizeTurn;
    }

    public Game(Board board, List<Player> players, GameVersion gameVersion, Turn customizeTurn) {
        this.board = board;
        this.players = players;
        this.gameVersion = gameVersion;
        this.customizeTurn = customizeTurn;
    }
    private List<Player> firstTurn(Player initial, List<Player> players) {
        List<Player> newList = new ArrayList<>();
        for (Player player : players) {
            if (player.getColor() == initial.getColor()) {
                newList.add(new Player(player.getName(), player.getColor(), true));
            } else {
                newList.add(player); //si no es el primer jugador, lo agrego normal
            }
        }
        return newList;
    }


    public Board getBoard() {
        return board;
    }

    @Override
    public Game move(Position oldPos, Position newPosition) {
        Player current = customizeTurn.getCurrent(players);
        Piece piece = oldPos.getPiece();
        if (validTurn(current, piece)) {
            Board tablero = current.movePiece( oldPos,newPosition, board);
            for (SpecialRules spec: gameVersion.getRules()){
                if (spec.validateMove(tablero,oldPos, newPosition) != tablero){
                    Board board1 = spec.validateMove(tablero, oldPos, newPosition);
                    return new Game(board1, changeTurn(this.players, tablero, tablero.getPosition(oldPos.getX(), oldPos.getY()), tablero.getPosition(newPosition.getX(), newPosition.getY())), gameVersion, customizeTurn);
                }
            }
            if (tablero == board) return this;

            for (Validators val: gameVersion.getValidators()){
                if (val.validateMove(this, tablero, current.getColor(), newPosition, oldPos) != this){
                    return this; //si encuentra un nuevo game (un nuevo movimiento) devuelvo el tablero de antes
                }
            }
            return new Game(tablero, changeTurn(this.players, tablero,  tablero.getPosition(oldPos.getX(), oldPos.getY()), tablero.getPosition(newPosition.getX(), newPosition.getY())), gameVersion, customizeTurn);
        }
        return this;
    }
    private List<Player> changeTurn (List <Player> player, Board board, Position oldPos, Position newPos){
        return customizeTurn.nextTurn(player, board, oldPos, newPos);
    }


    private static boolean validTurn(Player current, Piece piece) {
        return Objects.equals(piece.getColor(), current.getColor());
    }
    @Override
    public List<Player> getChessPlayers() {
        return players;
    }

    public List<Piece> getPiecies() {
        return board.getPiecies();
    }



    @Override
    public boolean validateVictory(List<Player> player, Board board) {
        for (VictoryValidator valid: gameVersion.getVictoryInt()){
            if (valid.validateVictory(player,board)){
                return true;
            }
        }
        return false;
    }

    public GameVersion getGameVersion() {
        return gameVersion;
    }

    public Turn getCustomizeTurn() {
        return customizeTurn;
    }
}
