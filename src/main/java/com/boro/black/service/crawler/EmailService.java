package com.boro.black.service.crawler;

import com.boro.black.dto.EmailDTO;
import com.boro.black.entity.crawler.Email;
import com.boro.black.service.ElementService;

import java.util.List;

/**
 * Created by petroborovets on 4/28/15.
 */
public interface EmailService extends ElementService<Email> {
    List<Email> filterEmails(List<Email> b);
    EmailDTO getDTO(Email email);
    List<EmailDTO> getDTOs(List<Email> emails);
}
