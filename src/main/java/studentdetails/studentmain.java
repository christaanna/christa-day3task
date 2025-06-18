package studentdetails;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.List;

public class studentmain {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create("mongodb://localhost:27017/");
        MongoDatabase database = client.getDatabase("studdetails");
        MongoCollection<Document> collection = database.getCollection("student");
        MongoCollection<Document> collection1 = database.getCollection("course");
        MongoCollection<Document> collection2 = database.getCollection("enrollment");

        Document doc = new Document("name" , "asha").append("email" , "asha@gmail.com").append("phone" , "7812367120");
        Document doc1 = new Document("name" , "arun").append("email" , "arun@gmail.com").append("phone" , "9012367120");
        Document doc2 = new Document("name" , "aami").append("email" , "aami@gmail.com").append("phone" , "7453367120");

        collection.insertMany(List.of(doc,doc1,doc2));

        Document doc3 = new Document("Coursename" , "BCA").append("Department" , "Computer");
        Document doc4 = new Document("Coursename" , "Bsc.Maths").append("Department" , "Science");
        Document doc5 = new Document("Coursename" , "Bsw").append("Department" , "Social");

        collection1.insertMany(List.of(doc3,doc4,doc5));

        Document doc6 = new Document("type" , "enrolled").append("Student" , doc).append("Course" , doc3);
        collection2.insertOne(doc6);
        Document doc8 = new Document("type" , "enrolled").append("Student" , doc1).append("Course" , doc4);
        collection2.insertOne(doc8);

         Document doc7 = new Document("type" , "referenced").append("Student" ,
                doc2.getObjectId("_id")).append("Course" ,  doc5.getObjectId("_id"));
        collection2.insertOne(doc7);

        Bson filter = Filters.eq("name", "arun");
        Bson update = Updates.set("name", "arjun");
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions()
                .returnDocument(ReturnDocument.AFTER);
        Document updatedDoc = collection.findOneAndUpdate(filter, update, options);

        if (updatedDoc != null) {
            System.out.println("\nUpdated Student:");
            System.out.println(updatedDoc.toJson());
        } else {
            System.out.println("No document found to update.");
        }
        Bson embeddedfilter = Filters.eq("student.name", "arun");
        Bson embeddedupdate = Updates.set("student.name", "arjun");
        collection.Update(filter, update);
        
        
        String resultCreateIndex = collection.createIndex(Indexes.ascending("name"));
        System.out.println(String.format("Index created: %s", resultCreateIndex));
        collection.createIndex(Indexes.ascending("name"));
        System.out.println("\n--- Students ---");
        for (Document studentDoc : collection.find()) {
            System.out.println(studentDoc.toJson());
        }

        System.out.println("\n--- Courses ---");
        for (Document courseDoc : collection1.find()) {
            System.out.println(courseDoc.toJson());
        }

        System.out.println("\n--- Enrollments ---");
        for (Document enrollDoc : collection2.find()) {
            System.out.println(enrollDoc.toJson());
        }

        client.close();
    }
}





