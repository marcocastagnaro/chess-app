package root.factory;

import root.checkers.game.CheckersTurn;
import root.checkers.game.NoMoveVictory;
import root.checkers.movements.ClassicMovement;
import root.checkers.movements.EatMovement;
import root.checkers.movements.ObligatoryValidator;
import root.chess.ChessTurn;
import root.chess.RandomTurn;
import root.common.Interfaces.Turn;
import root.common.classicMovements.DiagonalMove;
import root.common.classicMovements.HorizontalMove;
import root.common.classicMovements.StraightMove;
import root.chess.movements.CheckValidator;
import root.common.*;
import root.common.enums.Color;
import root.common.enums.Pieces;
import root.common.Interfaces.Validators;
import root.common.Interfaces.VictoryValidator;
import root.chess.movements.RoqueKing;
import root.common.specialRule.Coronation;
import root.chess.movements.CheckMateValidator;
import root.common.victory.EatAllPiecies;

import java.util.ArrayList;
import java.util.List;

public class Game_factory {
    public static Game create_normal_game_chess() {
        List<Player> playersList = new ArrayList(List.of(new Player("Player 1", Color.WHITE), new Player("Player 2", Color.BLACK)));
        List<Validators> validator = new ArrayList(List.of(new CheckValidator(Pieces.KING)));
        List<VictoryValidator> victoryMode = new ArrayList(List.of(new CheckMateValidator(Pieces.KING)));
        Coronation coron = new Coronation(Pieces.PAWN, new Piece(Pieces.QUEEN, Color.WHITE, List.of(new StraightMove(7, 7), new DiagonalMove(7, 7, 7, 7), new HorizontalMove(7, 7))));
        GameVersion gameVersion = new GameVersion("root/common", victoryMode, validator, new ArrayList<>(List.of(new RoqueKing(), coron)));

        ChessTurn customizeTurn = new ChessTurn();
        Board board = Board_factory.create_normal_board_chess();
        return new Game(board, playersList, gameVersion, playersList.get(0), customizeTurn);
    }

    public static Game create_normal_game_checkers() {
        List<Player> playersList = new ArrayList<>(List.of(new Player("Player 1", Color.WHITE), new Player("Player 2", Color.BLACK)));
        List<Validators> validator = new ArrayList<>(List.of(new ObligatoryValidator(2)));
        List<VictoryValidator> victoryMode = new ArrayList<>(List.of(new EatAllPiecies(), new NoMoveVictory()));
        Coronation coron = new Coronation(Pieces.PAWN, new Piece(Pieces.QUEEN, Color.WHITE, List.of(new ClassicMovement(1, 1), new EatMovement(2, 2))));
        GameVersion gameVersion = new GameVersion("root/common", victoryMode, validator, new ArrayList<>(List.of(coron)));

        CheckersTurn customizeTurn = new CheckersTurn(2);
        Board board = Board_factory.createNormalCheckersBoard();
        return new Game(board, playersList, gameVersion, playersList.get(0), customizeTurn);
    }

    public static Game create_alterantive_game_chess(){
        List<Player> playersList = new ArrayList<>(List.of(new Player("Player 1", Color.WHITE), new Player("Player 2", Color.BLACK)));
        List<Validators> validator = new ArrayList<>(List.of(new CheckValidator(Pieces.KING)));
        List<VictoryValidator> victoryMode = new ArrayList<>(List.of(new EatAllPiecies()));
        GameVersion gameVersion = new GameVersion("root/common", victoryMode, validator, new ArrayList<>());
        Turn customizeTurn = new RandomTurn();
        Board board = Board_factory.create_alternative_board_chess();
        return new Game(board, playersList, gameVersion, playersList.get(0), customizeTurn);
    }
    public static Game create_extensive_game_chess(){
        List<Player> playersList = new ArrayList<>(List.of(new Player("Player 1", Color.WHITE), new Player("Player 2", Color.BLACK)));
        List<Validators> validator = new ArrayList<>(List.of(new CheckValidator(Pieces.KING)));
        List<VictoryValidator> victoryMode = new ArrayList<>(List.of(new EatAllPiecies()));
        GameVersion gameVersion = new GameVersion("root/common", victoryMode, validator, new ArrayList<>());
        Turn customizeTurn = new ChessTurn();
        Board board = Board_factory.create_extensive_board_chess();
        return new Game(board, playersList, gameVersion, playersList.get(0), customizeTurn);
    }
    public static Game create_extensive_checkers(){
        List<Player> playersList = new ArrayList<>(List.of(new Player("Player 1", Color.WHITE), new Player("Player 2", Color.BLACK)));
        List<Validators> validator = new ArrayList<>(List.of(new ObligatoryValidator(2)));
        List<VictoryValidator> victoryMode = new ArrayList<>(List.of(new EatAllPiecies(), new NoMoveVictory()));
        Coronation coron = new Coronation(Pieces.PAWN, new Piece(Pieces.QUEEN, Color.WHITE, List.of(new ClassicMovement(1, 1), new EatMovement(2, 2))));
        GameVersion gameVersion = new GameVersion("root/common", victoryMode, validator, new ArrayList<>(List.of(coron)));

        CheckersTurn customizeTurn = new CheckersTurn(2);
        Board board = Board_factory.createExtensiveCheckersBoard();
        return new Game(board, playersList, gameVersion, playersList.get(0), customizeTurn);
    }
}
