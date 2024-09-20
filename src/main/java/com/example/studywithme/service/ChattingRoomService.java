package com.example.studywithme.service;

import com.example.studywithme.dto.*;
import com.example.studywithme.entity.ChattingParticipant;
import com.example.studywithme.entity.ChattingRoom;
import com.example.studywithme.entity.StudyPost;
import com.example.studywithme.entity.User;
import com.example.studywithme.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
@RequiredArgsConstructor
@Service
public class ChattingRoomService {

    private final ChattingParticipantRepository chattingParticipantRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final UsersRepository usersRepository;
    private final StudyPostRepository studyPostRepository;
    private final StudyPostService studyPostService;

    public ChatRoomResult findAllRoom(String userId) {
        List<ChatRoomDto> chatRoomDto = new ArrayList<>();
        System.out.println("oo");
        List<ChattingRoom> chatRooms= chattingParticipantRepository.findChatRoomsByUserId(userId);
        ChatRoomResult chatRoomResult = new ChatRoomResult();
        chatRooms.forEach(room -> {
            ChatRoomDto chatRoomDtoDto = new ChatRoomDto();
            chatRoomDtoDto.setRoomId(room.getChatroomId());
            chatRoomDtoDto.setName(room.getName());
            chatRoomDtoDto.setRecruitmentPost(room.getRecruitmentPost());
            chatRoomDto.add(chatRoomDtoDto);
        });
        chatRoomResult.setResult("success");
        chatRoomResult.setChatRoomDtos(chatRoomDto);
        return chatRoomResult;
    }
    public ChatRoomResult createChatRoom(String userid,Integer post_id){
        ChatRoomResult chatRoomResult = new ChatRoomResult();
        try
        {
            StudyPost studyPost = new StudyPost();
            Optional<StudyPost> studyPostOptional = studyPostRepository.findById(post_id);
            if(studyPostOptional.isPresent()){
                studyPost = studyPostOptional.get();
            }
            else
            {
                System.out.println("해당 post 타입을  찾을 수 없습니다.");
                chatRoomResult.setResult("fail");
                return chatRoomResult;
            }
            ChattingRoom chattingRoom=new ChattingRoom();
            chattingRoom.setRecruitmentPost(studyPost);
            chattingRoom.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            chattingRoom =chattingRoomRepository.save(chattingRoom);
            ChattingParticipant chattingParticipant=new ChattingParticipant();
            chattingParticipant.setChatRoom(chattingRoom);


            Optional<User> optionalUser=usersRepository.findById(userid);
            System.out.println(userid);
            System.out.println(chattingRoom);
            User user=new User();
            if (optionalUser.isPresent()) {
                 user = optionalUser.get();
            } else {
                System.out.println("해당 ID의 사용자를 찾을 수 없습니다.");
                chatRoomResult.setResult("fail");
                return chatRoomResult;
            }
            chattingParticipant.setUser(user);
            User user2=new User();
            user2.setUserId(studyPost.getUser().getUserId());
            chattingParticipant.setUser(user2);
            chattingParticipantRepository.save(chattingParticipant);
            ChatRoomDto nChatDto=new ChatRoomDto();
            nChatDto.setRoomId(chattingRoom.getChatroomId());
            nChatDto.setOriginUserId(studyPost.getUser().getUserId());
            List<ChatRoomDto> chatDtoList=new ArrayList<>();
            chatDtoList.add(nChatDto);
            chatRoomResult.setChatRoomDtos(chatDtoList);


            chatRoomResult.setResult("success");
        }catch (Exception e)
        {
            e.printStackTrace();
            chatRoomResult.setResult("fail");
        }
        return chatRoomResult;
    }
    @Transactional
    public ChatRoomResult deleteChatRoom(String userId,int roomId) {
        ChatRoomResult chatRoomResult = new ChatRoomResult();
        try {
            chattingParticipantRepository.deleteByChatRoomIdAndUserId(userId,roomId);
            System.out.println("삭제");
            chatRoomResult.setResult("success");
        } catch (Exception e)
        {
            e.printStackTrace();
            chatRoomResult.setResult("fail");
        }

        return chatRoomResult;
    }

}
