package chess.movesCalculator;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMovesCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        ChessGame.TeamColor myColor = board.getPiece(myPosition).getTeamColor();
        int direction;
        int PromotionRow;

        if (myColor == ChessGame.TeamColor.WHITE) {
            direction = 1;
            PromotionRow = 8;
        }
        else{
            direction = -1;
            PromotionRow = 1;
        }

//        Move forward one
        ChessPosition oneStepForward = new ChessPosition(myPosition.getRow() + direction, myPosition.getColumn());
        ChessPiece myPiecePlusOne = board.getPiece(oneStepForward);
        if (myPiecePlusOne == null){
            if(myPosition.getRow() >= 1 && myPosition.getRow() <= 8 && myPosition.getColumn() >= 1 && myPosition.getColumn() <= 8){
                if(oneStepForward.getRow() == PromotionRow){
                    for(ChessPiece.PieceType promotion : ChessPiece.PieceType.values()){
                        if(ChessPiece.PieceType.PAWN != promotion && ChessPiece.PieceType.KING != promotion){
                            moves.add(new ChessMove(myPosition, oneStepForward, promotion));
                        }

                    }
                }
                else{
                    moves.add(new ChessMove(myPosition, oneStepForward, null));
                }

            }
        }

//        move forward two
        ChessPosition twoStepForward = new ChessPosition(myPosition.getRow() + direction *2, myPosition.getColumn());
        ChessPiece myPieceTwoUp = board.getPiece(twoStepForward);
        if (myPiecePlusOne == null) {
            if(myPosition.getRow() == 2 && myColor == ChessGame.TeamColor.WHITE || myPosition.getRow() == 7 && myColor == ChessGame.TeamColor.BLACK) {
                if (board.getPiece(oneStepForward) == null && board.getPiece(twoStepForward) == null) {
                    moves.add(new ChessMove(myPosition, twoStepForward, null));
                }
            }
        }

//        capture piece
        int[] captureCol = {1, -1};
        for (int capCol : captureCol) {
            ChessPosition capturePosition = new ChessPosition(myPosition.getRow() + direction, myPosition.getColumn() + capCol);
            if(myPosition.getRow() >= 1 && myPosition.getRow() <= 8 && myPosition.getColumn() >= 1 && myPosition.getColumn() <= 8) {
                if(board.getPiece(capturePosition) != null && board.getPiece(capturePosition).getTeamColor() != myColor){
                    if(capturePosition.getRow() == PromotionRow){
                        for(ChessPiece.PieceType promotion : ChessPiece.PieceType.values()){
                            if(ChessPiece.PieceType.PAWN != promotion && ChessPiece.PieceType.KING != promotion){
                                moves.add(new ChessMove(myPosition, capturePosition, promotion));
                            }

                        }
                    }
                    else{
                        moves.add(new ChessMove(myPosition, capturePosition, null));
                    }
                }
            }
        }
        return moves;
    }
}
