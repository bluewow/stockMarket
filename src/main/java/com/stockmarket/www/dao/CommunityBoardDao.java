package com.stockmarket.www.dao;
//쿼리문...
import java.util.List;

import com.stockmarket.www.entity.CommunityBoard;

public interface CommunityBoardDao {
	List<CommunityBoard> getCommunityBoardList(int page, String field, String query, String stockCode, int loginId);
	List<CommunityBoard> getReplyList(int boardId);
	
	CommunityBoard getCommunityBoardDetail(int id);

	int getReplyCnt(String field, String query, String stockName);
	int lastReplyNum(int boardId);
	
	int insertCommunityBoard(CommunityBoard communityBoard);
	int updateCommunityBoard(CommunityBoard communityBoard);
	int deleteCommunityBoard(int boardId);

	int insertReply(CommunityBoard insertReply);
	int updateReply(CommunityBoard updateReply);
	int deleteReply(int replyId);
	int deleteReplys(int boardId);
	
	int selectInterestBoard(CommunityBoard selectInterestBoard);
	int deleteInterestBoard(CommunityBoard deleteInterestBoard);
	int insertInterestBoard(CommunityBoard insertInterestBoard);
	int deleteInterestBoards(int boardId);
}
