package root.factory;

import root.checkers.movements.ClassicMovement;
import root.checkers.movements.EatMovement;
import root.common.*;
import root.common.enums.Color;
import root.common.enums.Pieces;
import root.chess.movements.*;
import root.common.classicMovements.DiagonalMove;
import root.common.classicMovements.HorizontalMove;
import root.common.classicMovements.StraightMove;

import java.util.ArrayList;
import java.util.List;

public class Board_factory {

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
                    positions.add(new Position(i, j, new Piece(Pieces.PAWN, Color.BLACK, List.of(new ClassicMovement(0, 1), new EatMovement(0, 2)),
                            "W" + i + j)));
                }
                // Black pieces
                else if ((i + j) % 2 == 0 && i > 4) {
                    positions.add(new Position(i, j, new Piece(Pieces.PAWN, Color.WHITE,
                            List.of(new ClassicMovement(1,0 ), new EatMovement(2, 0)),
                            "B" + i + j)));
                }
            }
        }

        for (int i = 0; i < positions.size(); i++) {
            board.getBoard()[positions.get(i).getX()][positions.get(i).getY()] = positions.get(i);
        }

        return board;
    }
    public static Board create_alternative_board_chess(){
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
        positions.add(new Position(7, 1, new Piece(Pieces.CHANCELLOR, Color.WHITE, List.of(new Jump(1, 2), new Jump(2, 1),new HorizontalMove(7,7), new StraightMove(7,7)), "K1")));
        positions.add(new Position(7, 6, new Piece(Pieces.CHANCELLOR, Color.WHITE, List.of(new Jump(1, 2), new Jump(2, 1),new HorizontalMove(7,7), new StraightMove(7,7)), "K2")));
        positions.add(new Position(0, 1, new Piece(Pieces.CHANCELLOR, Color.BLACK, List.of(new Jump(1, 2), new Jump(2, 1), new HorizontalMove(7,7), new StraightMove(7,7)), "K3")));
        positions.add(new Position(0, 6, new Piece(Pieces.ARCHBISHOP, Color.BLACK, List.of(new Jump(1, 2), new Jump(2, 1),new HorizontalMove(7,7), new StraightMove(7,7)), "K4")));
        positions.add(new Position(7, 2, new Piece(Pieces.CHANCELLOR, Color.WHITE, List.of(new DiagonalMove(8, 8, 8, 8), new Jump(2,1), new Jump(1,2)), "B1")));
        positions.add(new Position(7, 5, new Piece(Pieces.ARCHBISHOP, Color.WHITE, List.of(new DiagonalMove(8, 8, 8, 8), new Jump(2,1), new Jump(1,2)), "B2")));
        positions.add(new Position(0, 2, new Piece(Pieces.ARCHBISHOP, Color.BLACK, List.of(new DiagonalMove(8, 8, 8, 8), new Jump(2,1), new Jump(1,2)), "B3")));
        positions.add(new Position(0, 5, new Piece(Pieces.ARCHBISHOP, Color.BLACK, List.of(new DiagonalMove(8, 8, 8, 8), new Jump(2,1), new Jump(1,2)), "B4")));
        positions.add(new Position(7, 4, new Piece(Pieces.QUEEN, Color.WHITE, List.of(new StraightMove(7, 7), new DiagonalMove(7, 7, 7, 7), new HorizontalMove(7, 7)), "Q1")));
        positions.add(new Position(0, 4, new Piece(Pieces.QUEEN, Color.BLACK, List.of(new StraightMove(7, 7), new DiagonalMove(7, 7, 7, 7), new HorizontalMove(7, 7)), "Q2")));
        positions.add(new Position(7, 3, new Piece(Pieces.KING, Color.WHITE, List.of(new StraightMove(1, 1), new DiagonalMove(1, 1, 1, 1), new HorizontalMove(1, 1)), "KI1")));
        positions.add(new Position(0, 3, new Piece(Pieces.KING, Color.BLACK, List.of(new StraightMove(1, 1), new DiagonalMove(1, 1, 1, 1), new HorizontalMove(1, 1)), "KI2")));

        for (int i = 0; i < positions.size(); i++) board.getBoard()[positions.get(i).getX()][positions.get(i).getY()] = positions.get(i);

        return board;
    }
    public static Board create_extensive_board_chess(){
        Board board = new Board(10, 10);
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            positions.add(new Position(8, i, new Piece(Pieces.PAWN, Color.WHITE, List.of(new PawnMovement(0, 1, 0, 0, 2, new PawnEatDiagonal(0, 1, 0, 0))), i + "8")));
            positions.add(new Position(1, i, new Piece(Pieces.PAWN, Color.BLACK, List.of(new PawnMovement(1, 0, 0, 0, 2, new PawnEatDiagonal(1, 0, 0, 0))), i + "1")));
        }

        positions.add(new Position(9, 0, new Piece(Pieces.ROOK, Color.WHITE, List.of(new StraightMove(9, 9), new HorizontalMove(9, 9)), "R1")));
        positions.add(new Position(9, 9, new Piece(Pieces.ROOK, Color.WHITE, List.of(new StraightMove(9, 9), new HorizontalMove(9, 9)), "R2")));
        positions.add(new Position(0, 0, new Piece(Pieces.ROOK, Color.BLACK, List.of(new StraightMove(9, 9), new HorizontalMove(9, 9)), "R3")));
        positions.add(new Position(0, 9, new Piece(Pieces.ROOK, Color.BLACK, List.of(new StraightMove(9, 9), new HorizontalMove(9, 9)), "R4")));

        // Add more pieces and their initial positions in the middle
        positions.add(new Position(5, 4, new Piece(Pieces.BISHOP, Color.WHITE, List.of(new DiagonalMove(10, 10, 10, 10)), "B5")));
        positions.add(new Position(5, 5, new Piece(Pieces.BISHOP, Color.BLACK, List.of(new DiagonalMove(10, 10, 10, 10)), "B6")));

        positions.add(new Position(6, 3, new Piece(Pieces.QUEEN, Color.WHITE, List.of(new StraightMove(10, 10), new DiagonalMove(10, 10, 10, 10), new HorizontalMove(10, 10)), "Q3")));
        positions.add(new Position(6, 6, new Piece(Pieces.QUEEN, Color.BLACK, List.of(new StraightMove(10, 10), new DiagonalMove(10, 10, 10, 10), new HorizontalMove(10, 10)), "Q4")));

        positions.add(new Position(7, 4, new Piece(Pieces.KNIGHT, Color.WHITE, List.of(new Jump(1, 2), new Jump(2, 1)), "K5")));
        positions.add(new Position(7, 5, new Piece(Pieces.KNIGHT, Color.BLACK, List.of(new Jump(1, 2), new Jump(2, 1)), "K6")));

        // Add more pieces and their initial positions as needed for the larger board

        for (int i = 0; i < positions.size(); i++) {
            board.getBoard()[positions.get(i).getX()][positions.get(i).getY()] = positions.get(i);
        }

        return board;
    }
        public static Board createExtensiveCheckersBoard() {
            Board board = new Board(12, 12);
            List<Position> positions = new ArrayList<>();

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 12; j++) {
                    // White pieces
                    if ((i + j) % 2 == 0 && i > 8) {
                        positions.add(new Position(i, j, new Piece(Pieces.PAWN, Color.WHITE,
                                List.of(new ClassicMovement(1, 0), new EatMovement(2, 0)),
                                "W" + i + j)));
                    }
                    // Black pieces
                    else if ((i + j) % 2 == 0 && i < 3) {
                        positions.add(new Position(i, j, new Piece(Pieces.PAWN, Color.BLACK,
                                List.of(new ClassicMovement(0, 1), new EatMovement(0, 2)),
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

