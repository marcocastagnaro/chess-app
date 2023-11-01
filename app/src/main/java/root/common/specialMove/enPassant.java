//package specialMove;
//
//import Chess.Game;
//import Classic.Piece;
//import Classic.Position;
//import Enums.Piecies;
//import Interfaces.specialMovementValidator;
//
//public class enPassant implements specialMovementValidator {
//    @Override
//    public Game validateMove(Game game, Position position, Position newPos) {
//        return null;
//    }
//
//    public boolean pawnEnPassant (Position oldPos, Position newPos, int dir){
//        Piece piece = oldPos.getPiece();
//        Position posPwn = new Position(oldPos.getX() + piece.getMovements(), newPos.getY()+ dir);
//        if (piece.getName() == Piecies.PAWN){
//            if (piece2.getName() == Piecies.PAWN){
//        }
//    }
//}
