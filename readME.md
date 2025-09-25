

# 📚 Project Name: Accounting Ledger CLI Application

## 🢾 Description

The Accounting Ledger CLI Application is a Java-based command-line tool that allows users to efficiently track and manage financial transactions for personal or small business use.  
The application supports deposits, payments, ledger viewing, and reporting features, all persisted to a local `transactions.csv` file.

This project was built to demonstrate clean architecture, object-oriented design principles, and robust file handling in Java.

---

## 🚀 Features

- Add deposits and payments
- View complete ledger entries (newest first)
- Filter entries by:
    - Deposits only
    - Payments only
- Generate financial reports:
    - Month-to-Date
    - Previous Month
    - Year-to-Date
    - Previous Year
    - Search by Vendor
- Current balance calculation
- Custom search (optional bonus feature)
- Persist data safely into a CSV file
- Robust error handling and validation

---

## 🛠️ Technologies Used

- Java 21
- Object-Oriented Programming (OOP)
- Java I/O (BufferedReader, FileWriter)
- Maven (for build automation) *(or Gradle)*
- JUnit 5 (for unit testing)
- Git and GitHub for version control

---

## 📂 Project Structure

```bash
src/
├── main/
│   ├── java/
│   │   └── com/ledgerapp/
│   │       ├── app/            # Application entry point
│   │       ├── model/          # Data models (Transaction.java)
│   │       ├── repository/     # Data persistence layer (CSV repository)
│   │       ├── service/        # Business logic (LedgerService)
│   │       ├── util/           # Utility classes (Date parsing, formatting)
│   │       └── view/           # CLI view classes (menus, displays)
│   └── resources/
│       └── transactions.csv    # Transaction data file
└── test/
    └── java/
        └── com/ledgerapp/      # Unit tests
```

---

## 📜 How to Run

1. Clone the repository:

```bash
git clone https://github.com/your-username/ledger-app.git
cd ledger-app
```

2. Build the project (using Maven):

```bash
mvn clean install
```

3. Run the application:

```bash
java -cp target/ledger-app-1.0-SNAPSHOT.jar com.ledgerapp.app.LedgerApplication
```

*(Adjust if using Gradle instead of Maven.)*

---

## 📸 Screenshots

### Home Screen:
```text
🏦 Accounting Ledger - Home Menu
D) Add Deposit
P) Make Payment
L) View Ledger
X) Exit
```

### Ledger Screen:
```text
📜 Ledger Menu
A) View All Transactions
D) View Deposits Only
P) View Payments Only
R) Run Reports
H) Return Home
```

---

## 💬 Interesting Piece of Code

The application dynamically calculates the **current balance** based on all saved transactions using a functional style:

```java
public BigDecimal getCurrentBalance() {
    List<Transaction> transactions = repository.findAll();
    return transactions.stream()
            .map(Transaction::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
}
```

This ensures the balance is always accurate without needing a separate "balance" field that could become out of sync.

---

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

# 🎯 Future Improvements (Optional)

- Implement password-protected access.
- Migrate storage from CSV to a lightweight embedded database (like H2 or SQLite).
- Use a CLI framework like Picocli for more advanced CLI interaction.
- Add more detailed search and filtering options.

---

# 🧐 Final Note

This project demonstrates good practices in structuring Java applications, focusing on clear separation of concerns, modularity, and maintainability — all critical skills for real-world software development.

---

# 📢 Quick Copyable Title for GitHub

> **Accounting Ledger CLI App (Java 21 | CSV Persistence | CLI Interface)**

