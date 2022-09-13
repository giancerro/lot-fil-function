package it.ringmaster.lotfil;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import it.ringmaster.lotfil.dto.TicketType;

public class AddTicketType {

    @FunctionName("AddTicketType")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS, route = "ticketType") HttpRequestMessage<Optional<TicketType>> request,
            @CosmosDBOutput(name = "database",
                    databaseName = "lot-fil-db",
                    collectionName = "TicketTypes",
                    connectionStringSetting = "Cosmos_DB_Connection_String",
                    createIfNotExists = true)
                    OutputBinding<TicketType> outputBinding,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        TicketType ticketType = request.getBody().orElse(null);

        if (ticketType == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a valid ticketType in request body").build();
        } else {
            outputBinding.setValue(ticketType);
            return request.createResponseBuilder(HttpStatus.OK).body(ticketType).build();
        }
    }
}
