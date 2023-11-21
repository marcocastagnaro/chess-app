package root.checkers.movements;

import org.jetbrains.annotations.Nullable;
import root.common.Board;
import root.common.enums.Color;
import root.common.Game;
import root.common.Interfaces.Validators;
import root.common.Piece;
import root.common.Position;

public class ObligatoryValidator implements Validators {
    int dif;

    public ObligatoryValidator(int dif) {
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
                        Game game1 = moveRightUp(game, board1, position1, piece);
                        if (game1 != game) return game1;
                        Game game2 = moveRightDown(game, board1, position1, piece);
                        if (game2 != game) return game2;
                        Game game3 = moveUpLeft(game, board1, position1, piece);
                        if (game3 != game) return game3;
                        Game game4 = moveLeftDown(game, board1, position1, piece);
                        if (game4 != game) return game4;
                    }
                }
            }
        }
        return game;
    }

    @Nullable
    private Game moveLeftDown(Game game, Board board1, Position position1, Piece piece) {
        Board newTablero;
        if (board1.getPosition(position1.getX() - dif, position1.getY() - dif) != null) {
            newTablero = board1.move(position1, board1.getPosition(position1.getX() - dif, position1.getY() - dif), piece);
            if (newTablero != board1) {
                return new Game(newTablero, game.getChessPlayers(), game.getGameVersion(), game.getCustomizeTurn());
            }
        }
        return game;
    }

    @Nullable
    private Game moveUpLeft(Game game, Board board1, Position position1, Piece piece) {
        Board newTablero;
        if (board1.getPosition(position1.getX() + dif, position1.getY() - dif) != null) {
            newTablero = board1.move(position1, board1.getPosition(position1.getX() + dif, position1.getY() - dif), piece);
            if (newTablero != board1) {
                return new Game(newTablero, game.getChessPlayers(), game.getGameVersion(), game.getCustomizeTurn());
            }
        }
        return game;
    }

    @Nullable
    private Game moveRightDown(Game game, Board board1, Position position1, Piece piece) {
        Board newTablero;
        if (board1.getPosition(position1.getX() - dif, position1.getY() + dif) != null) {
            newTablero = board1.move(position1, board1.getPosition(position1.getX() - dif, position1.getY() + dif), piece);
            if (newTablero != board1) {
                return new Game(newTablero, game.getChessPlayers(), game.getGameVersion(), game.getCustomizeTurn());
            }
        }
        return game;
    }

    @Nullable
    private Game moveRightUp(Game game, Board board1, Position position1, Piece piece) {
        Board newTablero;
        if (board1.getPosition(position1.getX() + dif, position1.getY() + dif) != null) {
            newTablero = board1.move(position1, board1.getPosition(position1.getX() + dif, position1.getY() + dif), piece);
            if (newTablero != board1) {
                return new Game(newTablero, game.getChessPlayers(), game.getGameVersion(), game.getCustomizeTurn());
            }
        }
        return game;
    }
}