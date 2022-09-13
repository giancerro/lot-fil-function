package it.ringmaster.lotfil;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import it.ringmaster.lotfil.dto.Ticket;
import it.ringmaster.lotfil.dto.TicketType;

public class GetTicket {

    @FunctionName("GetTicket")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS, route = "ticket/{userId}") HttpRequestMessage<Void> request,
            @CosmosDBInput(name = "database",
                    databaseName = "lot-fil-db",
                    collectionName = "Tickets",
                    sqlQuery = "select * from Tickets t where t.userId = {userId}",
                    connectionStringSetting = "Cosmos_DB_Connection_String")
                    List<Ticket> ticketList,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        return request.createResponseBuilder(HttpStatus.OK).body(ticketList).build();
    }
}
