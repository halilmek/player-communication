
---

## **Class Responsibilities**

### **1. `Player`**
- Represents a player in the communication system.
- Stores player name, incoming and outgoing message queues, and initiator status.
- Provides methods to access these properties.

### **2. `PlayerService`**
- Defines the contract for managing player communication.

### **3. `PlayerServiceImpl`**
- Implements the `PlayerService` interface.
- Handles:
    - Creating players.
    - Messaging between players.
    - Graceful program termination after the stop condition is met.

### **4. `PlayerController`**
- Acts as the intermediary between the user and the service layer.
- Starts the game and manages the communication flow.

### **5. `PlayerRepository`**
- Provides in-memory storage for `Player` instances.
- Supports operations to save and retrieve players by name.

### **6. `PlayerMapper`**
- Creates `MessageDTO` instances for communication.
- Formats messages with appropriate sender and content.

### **7. `MessageDTO`**
- A Data Transfer Object (DTO) that encapsulates message details.
- Includes sender name and message content.
- Provides a formatted `toString()` for display purposes.

### **8. `PlayerCommunication`**
- The main entry point of the application.
- Initializes the controller and starts the program.

---

## **How to Build and Run**

### **Building the Project**
1. Navigate to the project directory:
   ```bash
   cd /path/to/player-communication
    mvn clean package
   java -jar target/player-communication-1.0-SNAPSHOT.jar

