package chess.movesCalculator;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMovesCalculator implements PieceMovesCalculator{


    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        int [][] directions = {{1,1},{-1,1},{1,-1},{-1,-1}};

        for (int [] direction: directions){
            int row = myPosition.getRow();
            int col = myPosition.getColumn();
            ChessGame.TeamColor myColor = board.getPiece(myPosition).getTeamColor();

            while(true){
                row += direction[0];
                col += direction[1];

                if (row < 1 || row >= 9 || col < 1 || col >= 9){
                    break;
                }

                ChessPosition newPosition = new ChessPosition(row, col);
                ChessPiece newPositionPiece = board.getPiece(newPosition);
                if(newPositionPiece == null) {
                    moves.add(new ChessMove(myPosition, newPosition, null));
                }
                else if (newPositionPiece.getTeamColor() != myColor){
                    moves.add(new ChessMove(myPosition, newPosition, null));
                    break;
                }
                else{
                    break;
                }
            }
        }
        return moves;
    }
}
