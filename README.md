 Student Management System (MongoDB + Java)

This Java application demonstrates basic **CRUD operations** on a student-course-enrollment system using **MongoDB** with both **embedded** and **referenced** document models.

---

## 🚀 Features

✅ Insert student and course data  
✅ Create enrollments using both **embedded** and **referenced** models  
✅ Update a student's name and return the updated document  
✅ Automatically update **referenced** documents  
✅ Manually update **embedded** documents if needed  
✅ Create **indexes** on student fields for faster querying  
✅ Display all documents from all collections in the console  

---

 Tech Stack

- Java 8+
- MongoDB (local instance)
- MongoDB Java Driver (`mongodb-driver-sync`)

---

Collections

- `student`: Stores student details (name, email, phone)
- `course`: Stores course info (course name, department)
- `enrollment`: Stores enrollment data:
  - `type: "embedded"`: Full copies of student & course docs
  - `type: "referenced"`: ObjectId references to student and course

---

 Document Update Logic

 Updating a Referenced Document


Bson filter = Filters.eq("name", "arun");
Bson update = Updates.set("name", "arjun");
FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
Document updatedDoc = collection.findOneAndUpdate(filter, update, options);


Changes automatically reflect in `enrollment` documents that reference the student by ObjectId.

---

 Updating an Embedded Document

Embedded data (deep copies inside another document) do **not** reflect external updates.

To reflect changes in embedded enrollments, you must update them manually:

Bson embeddedFilter = Filters.eq("student.name", "arun");
Bson embeddedUpdate = Updates.set("student.name", "arjun");
enrollmentCollection.updateMany(embeddedFilter, embeddedUpdate);


---

Indexing

To improve query performance:

String resultCreateIndex = collection.createIndex(Indexes.ascending("name"));
        System.out.println(String.format("Index created: %s", resultCreateIndex));
        collection.createIndex(Indexes.ascending("name"));

---

 How to Run

1. Make sure **MongoDB is running locally** at `mongodb://localhost:27017/`
2. Use Maven or your IDE to include this dependency:
<dependency>
  <groupId>org.mongodb</groupId>
  <artifactId>mongodb-driver-sync</artifactId>
  <version>4.11.0</version>
</dependency>


3. Compile and run `studentmain.java`
4. View the output in the console

---

 Sample Output

![Screenshot 2025-06-18 162018](https://github.com/user-attachments/assets/cbfe6d85-dafa-4d6c-a2ec-d07b32c712c4)
![Screenshot 2025-06-18 162150](https://github.com/user-attachments/assets/aa5c51ef-97a5-4384-9b69-fa7bae2b5d8c)
![Screenshot 2025-06-18 162208](https://github.com/user-attachments/assets/bfdeb514-5a3b-4f01-b6a8-4c5117e010f3)
![Screenshot 2025-06-18 162036](https://github.com/user-attachments/assets/b6c9399a-b86f-4c05-a7bf-14a48d44fbf6)



Learning Outcomes

* How MongoDB handles embedded vs. referenced data
* Performing updates and returns in MongoDB Java
* Index creation for performance optimization
* Real-time data modeling for academic use cases

---

*Author

Christa Anna
GitHub: [@christaanna](https://github.com/christaanna)
