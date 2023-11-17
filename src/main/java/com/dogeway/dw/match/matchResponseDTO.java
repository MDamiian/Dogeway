package com.dogeway.dw.match;


public record matchResponseDTO(Long id_match, Long id_user, Long id_match_user, boolean status_user, boolean status_match) {

    public matchResponseDTO(match matchs) {
        this(matchs.getId_match(),matchs.getId_user(), matchs.getId_user_match(), matchs.isStatus_user(), matchs.isStatus_user_match());
    }
}
