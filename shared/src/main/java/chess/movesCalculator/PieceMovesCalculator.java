package chess.movesCalculator;

import chess.ChessMove;
import chess.ChessPosition;
import chess.ChessPiece;
import chess.ChessBoard;
import chess.ChessGame;

import java.util.Collection;


public interface PieceMovesCalculator {
    Collection<ChessMove>pieceMoves(ChessBoard board, ChessPosition myPosition);
}
