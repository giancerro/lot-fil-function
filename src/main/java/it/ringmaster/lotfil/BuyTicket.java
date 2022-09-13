package it.ringmaster.lotfil;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import it.ringmaster.lotfil.dto.Ticket;

public class BuyTicket {

    @FunctionName("BuyTicket")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS, route = "ticket") HttpRequestMessage<Optional<Ticket>> request,
            @CosmosDBOutput(name = "database",
                    databaseName = "lot-fil-db",
                    collectionName = "Tickets",
                    connectionStringSetting = "Cosmos_DB_Connection_String",
                    createIfNotExists = true)
                    OutputBinding<Ticket> outputBinding,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        Ticket ticket = request.getBody().orElse(null);

        if (ticket == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a valid ticket in request body").build();
        } else {
            ticket.setUserId(request.getHeaders().get("x-ms-client-principal-id"));
            outputBinding.setValue(ticket);
            return request.createResponseBuilder(HttpStatus.OK).body(ticket).build();
        }
    }
}
