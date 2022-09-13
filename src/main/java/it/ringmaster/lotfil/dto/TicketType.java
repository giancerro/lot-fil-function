package it.ringmaster.lotfil.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketType {

    private String id;
    private String title;
    private String subtitle;
    private String description;

}
