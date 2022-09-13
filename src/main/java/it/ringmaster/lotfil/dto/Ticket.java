package it.ringmaster.lotfil.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private String id;
    private String userId;
    private String ticketTypeId;

}
