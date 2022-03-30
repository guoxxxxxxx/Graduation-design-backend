package com.hebust.service.imple;

import com.hebust.mapper.MailVerifMapper;
import com.hebust.service.MailVerifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailVerifServiceImpl implements MailVerifService {

    @Autowired
    private MailVerifMapper mapper;

    @Override
    public String insertRecord(String email) {
        return null;
    }

    @Override
    public String selectRandomCodeByEmail(String email) {
        return null;
    }

    @Override
    public String updateRandomCodeByEmail(String email) {
        return null;
    }

    @Override
    public int deleteRecordByEmail(String email) {
        return 0;
    }
}
