package com.gidimo.librarymicroservice.services;

import com.gidimo.librarymicroservice.db.entity.MemberEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface MemberService {
    Optional<MemberEntity> getMemberById(final Long memberId);
}
