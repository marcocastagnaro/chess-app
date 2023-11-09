package root.chess;

import root.common.Board;
import root.common.Game;
import root.common.Interfaces.validators;
import root.common.Piece;
import root.common.Position;
import root.common.Enums.Color;
import root.common.Enums.Piecies;

import java.util.Objects;

public class checkValidator implements validators {
    private Piecies name;

    public checkValidator(Piecies name) {
        this.name = name;
    }

    public boolean pointTo(Position posAct, Board board){ //devuelve true si apunta a un rey de otro color
        Piece piece = posAct.getPiece();
        for (int x = 0; x < board.getRow(); x++) {
            for (int y = 0; y < board.getColumn(); y++) {
                Position possiblePos = board.getPosition(x,y);
                if (possiblePos.hasPiece() && possiblePos.getColor() != piece.getColor() && possiblePos.getPiece().getName() == name){
                        if (piece.moveValidator(posAct, possiblePos, board)){
                            return true;
                        }
                    }
                }
            }
        return false;
    }
    public boolean validateMove(Board board, Color color){ //al hacer el movimiento chequear si esta en jaque.
        for (int x=0; x < board.getRow(); x++){
            for (int y=0; y< board.getColumn(); y++){
                if (board.getPiece (x,y) != null ) {
                    if (!Objects.equals(board.getPiece(x, y).getColor(), color)) {
                        if (this.pointTo(board.getPosition(x,y), board)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Game validateMove(Game game, Board board1, Color color, Position newPos, Position oldPos) {
        if (validateMove(board1, color)){
            return new Game(board1, game.getChessPlayers(), game.getGameVersion(),game.getCustomizeTurn());
        }
        return game;
    }
}


//interfaces importantes: Cliente.  Mandar un mensaje SOLO al server. Conectarse y cerrrar la conexion.
//Server --> EMPEZAR A ESCUCHAR UN PUERTO.Detenerse (liberar el puerto). Send message (mandar un mensaje). Hcaer un broadcast (se lo manda a todos los clientes)
//Message listener (interfaz importante) --> dos o tres implementaciones.
//Serverconnection listener --> Listener de la conexion. Cuando se conecta un cliente, cuando se desconecta un cliente.
//Message --> Type y payload(una clase concreta, instancia).
//ServerBuilder -->
//ClientBuilder -->

//primero crear servidor y settear tdos los lsiteners que queremos.
//creas un cliente, le setteas el puetrto y losm,listeneers. Build.