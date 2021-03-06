package edu.dartmouth.cs.pantryplanner.backend.endpoint;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import edu.dartmouth.cs.pantryplanner.backend.entity.MealPlanRecord;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "mealPlanRecordApi",
        version = "v1",
        resource = "mealPlanRecord",
        namespace = @ApiNamespace(
                ownerDomain = "entity.backend.pantryplanner.cs.dartmouth.edu",
                ownerName = "entity.backend.pantryplanner.cs.dartmouth.edu",
                packagePath = ""
        )
)
public class MealPlanRecordEndpoint {

    private static final Logger logger = Logger.getLogger(MealPlanRecordEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    /**
     * Returns the {@link MealPlanRecord} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code MealPlanRecord} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "mealPlanRecord/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public MealPlanRecord get(@Named("id") Long id) throws NotFoundException {
        logger.info("Getting MealPlanRecord with ID: " + id);
        MealPlanRecord mealPlanRecord = ofy().load().type(MealPlanRecord.class).id(id).now();
        if (mealPlanRecord == null) {
            throw new NotFoundException("Could not find MealPlanRecord with ID: " + id);
        }
        return mealPlanRecord;
    }

    /**
     * Inserts a new {@code MealPlanRecord}.
     */
    @ApiMethod(
            name = "insert",
            path = "mealPlanRecord",
            httpMethod = ApiMethod.HttpMethod.POST)
    public MealPlanRecord insert(MealPlanRecord mealPlanRecord) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that mealPlanRecord.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(mealPlanRecord).now();
        logger.info("Created MealPlanRecord with ID: " + mealPlanRecord.getId());

        return ofy().load().entity(mealPlanRecord).now();
    }

    /**
     * Updates an existing {@code MealPlanRecord}.
     *
     * @param id             the ID of the entity to be updated
     * @param mealPlanRecord the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code MealPlanRecord}
     */
    @ApiMethod(
            name = "update",
            path = "mealPlanRecord/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public MealPlanRecord update(@Named("id") Long id, MealPlanRecord mealPlanRecord) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(mealPlanRecord).now();
        logger.info("Updated MealPlanRecord: " + mealPlanRecord);
        return ofy().load().entity(mealPlanRecord).now();
    }

    /**
     * Deletes the specified {@code MealPlanRecord}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code MealPlanRecord}
     */
    @ApiMethod(
            name = "remove",
            path = "mealPlanRecord/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") Long id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(MealPlanRecord.class).id(id).now();
        logger.info("Deleted MealPlanRecord with ID: " + id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "mealPlanRecord",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<MealPlanRecord> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<MealPlanRecord> query = ofy().load().type(MealPlanRecord.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<MealPlanRecord> queryIterator = query.iterator();
        List<MealPlanRecord> mealPlanRecordList = new ArrayList<MealPlanRecord>(limit);
        while (queryIterator.hasNext()) {
            mealPlanRecordList.add(queryIterator.next());
        }
        return CollectionResponse.<MealPlanRecord>builder().setItems(mealPlanRecordList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    @ApiMethod(
            name = "listWith"
    )
    public CollectionResponse<MealPlanRecord> listWith(@Named("email") String email) {
        List<MealPlanRecord> mealPlanRecordList = ofy().load().type(MealPlanRecord.class).filter("email", email).list();
        logger.info("list with email " + email + "and found " + mealPlanRecordList.size() + " record.");
        return CollectionResponse.<MealPlanRecord>builder().setItems(mealPlanRecordList).build();
    }

    private void checkExists(Long id) throws NotFoundException {
        try {
            ofy().load().type(MealPlanRecord.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find MealPlanRecord with ID: " + id);
        }
    }
}