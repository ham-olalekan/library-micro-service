package com.gidimo.librarymicroservice.services.impl;

import com.gidimo.librarymicroservice.db.entity.MemberEntity;
import com.gidimo.librarymicroservice.db.repository.MemberRepository;
import com.gidimo.librarymicroservice.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository repository;

    @Autowired
    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<MemberEntity> getMemberById(final Long memberId) {
        return repository.findById(memberId);
    }
}
