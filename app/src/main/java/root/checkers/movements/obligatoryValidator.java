package root.checkers.movements;

import root.common.Board;
import root.common.Enums.Color;
import root.common.Game;
import root.common.Interfaces.validators;
import root.common.Piece;
import root.common.Position;

public class obligatoryValidator implements validators {
    int dif;

    public obligatoryValidator(int dif) {
        this.dif = dif;
    }


    private static boolean isValid(Color color, Position position1) {
        return position1.hasPiece() && position1.getColor() == color;
    }



    @Override
    public Game validateMove(Game game, Board board, Color color, Position newPos, Position oldPos) {
        Board board1 = game.getBoard().copy();
        Position delta = oldPos.delta(newPos);
        if (delta.getX()<2 && delta.getY() < 2) {
            for (int x = 0; x < board1.getRow(); x++) {
                for (int y = 0; y < board1.getColumn(); y++) {
                    Position position1 = board1.getPosition(x, y);
                    if (isValid(color, position1)) {
                        Piece piece = position1.getPiece();
                        Board newTablero;
                        if (board1.getPosition(position1.getX() + dif, position1.getY() + dif) != null) {
                            newTablero = board1.move(position1, board1.getPosition(position1.getX() + dif, position1.getY() + dif), piece);
                            if (newTablero != board1) {
                                return new Game(newTablero, game.getChessPlayers(), game.getGameVersion(), game.getCustomizeTurn());
                            }
                        }
                        if (board1.getPosition(position1.getX() - dif, position1.getY() + dif) != null) {

                            newTablero = board1.move(position1, board1.getPosition(position1.getX() - dif, position1.getY() + dif), piece);
                            if (newTablero != board1) {
                                return new Game(newTablero, game.getChessPlayers(), game.getGameVersion(), game.getCustomizeTurn());
                            }
                        }
                        if (board1.getPosition(position1.getX() + dif, position1.getY() - dif) != null) {

                            newTablero = board1.move(position1, board1.getPosition(position1.getX() + dif, position1.getY() - dif), piece);
                            if (newTablero != board1) {
                                return new Game(newTablero, game.getChessPlayers(), game.getGameVersion(), game.getCustomizeTurn());
                            }
                        }
                        if (board1.getPosition(position1.getX() - dif, position1.getY() - dif) != null) {
                            newTablero = board1.move(position1, board1.getPosition(position1.getX() - dif, position1.getY() - dif), piece);
                            if (newTablero != board1) {
                                return new Game(newTablero, game.getChessPlayers(), game.getGameVersion(), game.getCustomizeTurn());
                            }
                        }
                    }
                }
            }
        }
            return game;
        }
    }