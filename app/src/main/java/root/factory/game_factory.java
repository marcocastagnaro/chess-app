package root.factory;

import root.checkers.game.checkersTurn;
import root.checkers.game.noMoveVictory;
import root.checkers.movements.classicMovement;
import root.checkers.movements.eatMovement;
import root.checkers.movements.obligatoryValidator;
import root.chess.ChessTurn;
import root.chess.RandomTurn;
import root.common.Interfaces.turn;
import root.common.classicMovements.DiagonalMove;
import root.common.classicMovements.HorizontalMove;
import root.common.classicMovements.StraightMove;
import root.chess.Movements.checkValidator;
import root.common.*;
import root.common.Enums.Color;
import root.common.Enums.Pieces;
import root.common.Interfaces.validators;
import root.common.Interfaces.victoryValidator;
import root.chess.Movements.RoqueKing;
import root.common.specialRule.coronacion;
import root.common.victory.checkMateValidator;
import root.common.victory.eatAllPiecies;

import java.util.ArrayList;
import java.util.List;

public class game_factory {
    public static Game create_normal_game_chess() {
        List<Player> playersList = new ArrayList(List.of(new Player("Player 1", Color.WHITE), new Player("Player 2", Color.BLACK)));
        List<validators> validator = new ArrayList(List.of(new checkValidator(Pieces.KING)));
        List<victoryValidator> victoryMode = new ArrayList(List.of(new checkMateValidator(Pieces.KING)));
        coronacion coron = new coronacion(Pieces.PAWN, new Piece(Pieces.QUEEN, Color.WHITE, List.of(new StraightMove(7, 7), new DiagonalMove(7, 7, 7, 7), new HorizontalMove(7, 7))));
        GameVersion gameVersion = new GameVersion("root/common", victoryMode, validator, new ArrayList<>(List.of(new RoqueKing(), coron)));

        ChessTurn customizeTurn = new ChessTurn();
        Board board = board_factory.create_normal_board_chess();
        return new Game(board, playersList, gameVersion, playersList.get(0), customizeTurn);
    }

    public static Game create_normal_game_checkers() {
        List<Player> playersList = new ArrayList<>(List.of(new Player("Player 1", Color.WHITE), new Player("Player 2", Color.BLACK)));
        List<validators> validator = new ArrayList<>(List.of(new obligatoryValidator(2)));
        List<victoryValidator> victoryMode = new ArrayList<>(List.of(new eatAllPiecies(), new noMoveVictory()));
        coronacion coron = new coronacion(Pieces.PAWN, new Piece(Pieces.QUEEN, Color.WHITE, List.of(new classicMovement(1, 1), new eatMovement(2, 2))));
        GameVersion gameVersion = new GameVersion("root/common", victoryMode, validator, new ArrayList<>(List.of(coron)));

        checkersTurn customizeTurn = new checkersTurn(2);
        Board board = board_factory.createNormalCheckersBoard();
        return new Game(board, playersList, gameVersion, playersList.get(0), customizeTurn);
    }

    public static Game create_alterantive_game_chess(){
        List<Player> playersList = new ArrayList<>(List.of(new Player("Player 1", Color.WHITE), new Player("Player 2", Color.BLACK)));
        List<validators> validator = new ArrayList<>(List.of(new checkValidator(Pieces.KING)));
        List<victoryValidator> victoryMode = new ArrayList<>(List.of(new eatAllPiecies()));
        GameVersion gameVersion = new GameVersion("root/common", victoryMode, validator, new ArrayList<>());
        turn customizeTurn = new RandomTurn();
        Board board = board_factory.create_alternative_board_chess();
        return new Game(board, playersList, gameVersion, playersList.get(0), customizeTurn);
    }
    public static Game create_extensive_game_chess(){
        List<Player> playersList = new ArrayList<>(List.of(new Player("Player 1", Color.WHITE), new Player("Player 2", Color.BLACK)));
        List<validators> validator = new ArrayList<>(List.of(new checkValidator(Pieces.KING)));
        List<victoryValidator> victoryMode = new ArrayList<>(List.of(new eatAllPiecies()));
        GameVersion gameVersion = new GameVersion("root/common", victoryMode, validator, new ArrayList<>());
        turn customizeTurn = new ChessTurn();
        Board board = board_factory.create_extensive_board_chess();
        return new Game(board, playersList, gameVersion, playersList.get(0), customizeTurn);
    }
    public static Game create_extensive_checkers(){
        List<Player> playersList = new ArrayList<>(List.of(new Player("Player 1", Color.WHITE), new Player("Player 2", Color.BLACK)));
        List<validators> validator = new ArrayList<>(List.of(new obligatoryValidator(2)));
        List<victoryValidator> victoryMode = new ArrayList<>(List.of(new eatAllPiecies(), new noMoveVictory()));
        coronacion coron = new coronacion(Pieces.PAWN, new Piece(Pieces.QUEEN, Color.WHITE, List.of(new classicMovement(1, 1), new eatMovement(2, 2))));
        GameVersion gameVersion = new GameVersion("root/common", victoryMode, validator, new ArrayList<>(List.of(coron)));

        checkersTurn customizeTurn = new checkersTurn(2);
        Board board = board_factory.createExtensiveCheckersBoard();
        return new Game(board, playersList, gameVersion, playersList.get(0), customizeTurn);
    }
}
