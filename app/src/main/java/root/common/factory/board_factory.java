package root.common.factory;

import root.checkers.movements.classicMovement;
import root.checkers.movements.eatMovement;
import root.common.*;
import root.common.Enums.Color;
import root.common.Enums.Pieces;
import root.chess.Movements.*;
import root.common.classicMovements.DiagonalMove;
import root.common.classicMovements.HorizontalMove;
import root.common.classicMovements.StraightMove;

import java.util.ArrayList;
import java.util.List;

public class board_factory {

    public static Board create_normal_board_chess(){
        Board board = new Board(8,8);
            List<Position> positions = new ArrayList<>();

            for (int i = 0; i < 8; i++) {
                positions.add(new Position(6, i, new Piece(Pieces.PAWN, Color.WHITE, List.of(new PawnMovement(0, 1, 0, 0, 2, new PawnEatDiagonal(0, 1, 0, 0))), i + "6")));
                positions.add(new Position(1, i, new Piece(Pieces.PAWN, Color.BLACK, List.of(new PawnMovement(1, 0, 0, 0, 2, new PawnEatDiagonal(1, 0, 0, 0))), i + "1")));
            }

            positions.add(new Position(7, 0, new Piece(Pieces.ROOK, Color.WHITE, List.of(new StraightMove(7, 7), new HorizontalMove(7, 7)), "R1")));
            positions.add(new Position(7, 7, new Piece(Pieces.ROOK, Color.WHITE, List.of(new StraightMove(7, 7), new HorizontalMove(7, 7)), "R2")));
            positions.add(new Position(0, 0, new Piece(Pieces.ROOK, Color.BLACK, List.of(new StraightMove(7, 7), new HorizontalMove(7, 7)), "R3")));
            positions.add(new Position(0, 7, new Piece(Pieces.ROOK, Color.BLACK, List.of(new StraightMove(7, 7), new HorizontalMove(7, 7)), "R4")));
            positions.add(new Position(7, 1, new Piece(Pieces.KNIGHT, Color.WHITE, List.of(new Jump(1, 2), new Jump(2, 1)), "K1")));
            positions.add(new Position(7, 6, new Piece(Pieces.KNIGHT, Color.WHITE, List.of(new Jump(1, 2), new Jump(2, 1)), "K2")));
            positions.add(new Position(0, 1, new Piece(Pieces.KNIGHT, Color.BLACK, List.of(new Jump(1, 2), new Jump(2, 1)), "K3")));
            positions.add(new Position(0, 6, new Piece(Pieces.KNIGHT, Color.BLACK, List.of(new Jump(1, 2), new Jump(2, 1)), "K4")));
            positions.add(new Position(7, 2, new Piece(Pieces.BISHOP, Color.WHITE, List.of(new DiagonalMove(8, 8, 8, 8)), "B1")));
            positions.add(new Position(7, 5, new Piece(Pieces.BISHOP, Color.WHITE, List.of(new DiagonalMove(8, 8, 8, 8)), "B2")));
            positions.add(new Position(0, 2, new Piece(Pieces.BISHOP, Color.BLACK, List.of(new DiagonalMove(8, 8, 8, 8)), "B3")));
            positions.add(new Position(0, 5, new Piece(Pieces.BISHOP, Color.BLACK, List.of(new DiagonalMove(8, 8, 8, 8)), "B4")));
            positions.add(new Position(7, 4, new Piece(Pieces.QUEEN, Color.WHITE, List.of(new StraightMove(7, 7), new DiagonalMove(7, 7, 7, 7), new HorizontalMove(7, 7)), "Q1")));
            positions.add(new Position(0, 4, new Piece(Pieces.QUEEN, Color.BLACK, List.of(new StraightMove(7, 7), new DiagonalMove(7, 7, 7, 7), new HorizontalMove(7, 7)), "Q2")));
            positions.add(new Position(7, 3, new Piece(Pieces.KING, Color.WHITE, List.of(new StraightMove(1, 1), new DiagonalMove(1, 1, 1, 1), new HorizontalMove(1, 1)), "KI1")));
            positions.add(new Position(0, 3, new Piece(Pieces.KING, Color.BLACK, List.of(new StraightMove(1, 1), new DiagonalMove(1, 1, 1, 1), new HorizontalMove(1, 1)), "KI2")));

            for (int i = 0; i < positions.size(); i++) board.getBoard()[positions.get(i).getX()][positions.get(i).getY()] = positions.get(i);

            return board;
        }
    public static Board createNormalCheckersBoard() {
        Board board = new Board(8, 8);
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // White pieces
                if ((i + j) % 2 == 0 && i < 3) {
                    positions.add(new Position(i, j, new Piece(Pieces.PAWN, Color.BLACK,
                            List.of(new classicMovement(0, 1), new eatMovement(0, 2)),
                            "W" + i + j)));
                }
                // Black pieces
                else if ((i + j) % 2 == 0 && i > 4) {
                    positions.add(new Position(i, j, new Piece(Pieces.PAWN, Color.WHITE,
                            List.of(new classicMovement(1,0 ), new eatMovement(2, 0)),
                            "B" + i + j)));
                }
            }
        }

        for (int i = 0; i < positions.size(); i++) {
            board.getBoard()[positions.get(i).getX()][positions.get(i).getY()] = positions.get(i);
        }

        return board;
    }


}
