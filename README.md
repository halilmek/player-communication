
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

### **8. `MultiProcessPlayerRunner`**
- Runs two players in separate processes (different PIDs).
- Manages the exchange of messages between the players.
- Starts both processes, reads messages from one player, and sends them to the other.
- Gracefully terminates the processes after reaching the maximum message limit.

### **9. `SingleProcessPlayerRunner`**
- Runs all players within a single process (same PID).
- Uses the PlayerService interface to initiate and manage the communication loop.

### **10. `PlayerProcess`**
- Represents a player running in its own separate process.
- Handles receiving messages, processing them, and sending responses.
- Adapts to roles such as “Initiator” or “Receiver” for message exchange.
- Exits when the pre-defined message count is reached.

### **11. `PlayerCommunication`**
- The main entry point of the application.
- Determines whether to run the program in single-process or multi-process mode based on command-line arguments.
- Starts the appropriate runner (MultiProcessPlayerRunner or SingleProcessPlayerRunner).

---
### ** When the app runs, it will show the PIDs.

## **How to Build and Run**

### **Building the Project**
1. Navigate to the project directory:
   ```bash
   cd /path/to/player-communication

2. Clean cache and re-install maven package 
   
       mvn clean package

3. Run the jar file under target folder after creating it with the previous command
    a. For single PID

          java -jar target/player-communication-1.0-SNAPSHOT.jar 
            or
            java -jar target/player-communication-1.0-SNAPSHOT.jar single
    b. For multiple PID

       
    cd /path/to/player-communication
          mvn clean package
         java -jar target/player-communication-1.0-SNAPSHOT.jar 

### **Scripts Overview

run.sh (For Mac/Linux)

This shell script automates the Maven build process and allows you to select the desired mode (Single Process or Multi-Process) interactively. It performs the following steps:
	•	Builds the project using Maven.
	•	Prompts you to select between Single Process or Multi-Process mode.
	•	Runs the program in the selected mode.

1.	Make the script executable:
    chmod +x run.sh

2.	Run the script:
    ./run.sh

run.bat (For Windows)

This batch script provides the same functionality as run.sh but is designed for Windows environments. It automates the build process and allows you to select the execution mode interactively.

1.	Open a command prompt and navigate to the project directory:
    cd \path\to\player-communication

2.	Run the script:
    run.bat
