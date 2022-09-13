package it.ringmaster.lotfil;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import it.ringmaster.lotfil.dto.TicketType;

public class GetAllTicketType {

    @FunctionName("GetAllTicketType")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS, route = "ticketType") HttpRequestMessage<Void> request,
            @CosmosDBInput(name = "database",
                    databaseName = "lot-fil-db",
                    collectionName = "TicketTypes",
                    sqlQuery = "select t.id, t.name, t.title from TicketTypes t",
                    connectionStringSetting = "Cosmos_DB_Connection_String")
                    List<TicketType> ticketTypeList,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        return request.createResponseBuilder(HttpStatus.OK).body(ticketTypeList).build();
    }
}
