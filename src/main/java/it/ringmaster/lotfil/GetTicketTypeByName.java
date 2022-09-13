package it.ringmaster.lotfil;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import it.ringmaster.lotfil.dto.TicketType;

public class GetTicketTypeByName {

    @FunctionName("GetTicketTypeByName")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = { HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "ticketType/{title}") HttpRequestMessage<Void> request,
            @CosmosDBInput(name = "database",
                    databaseName = "lot-fil-db",
                    collectionName = "TicketTypes",
                    sqlQuery = "select * from TicketTypes t where t.title = {title}",
                    partitionKey = "id",
                    connectionStringSetting = "Cosmos_DB_Connection_String")
                    List<TicketType> ticketTypeList,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        if (ticketTypeList == null || ticketTypeList.size() == 0) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a valid name").build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body(ticketTypeList.get(0)).build();
        }
    }
}
