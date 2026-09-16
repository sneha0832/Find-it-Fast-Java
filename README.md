# 🔎 Find It Fast — Java String Search Algorithms

A Java implementation and performance comparison of three classic string-search algorithms: **Brute Force, Knuth–Morris–Pratt (KMP), and Boyer–Moore**.

The program searches for a user-provided substring within a text file, reports every matching index, and measures the execution time of each algorithm.

## ✨ Features

* Implements **three string-search algorithms**

  * Brute Force
  * Knuth–Morris–Pratt (KMP)
  * Boyer–Moore
* Finds **all occurrences** of a pattern in the input text
* Reports the starting index of each match
* Measures algorithm runtime using `System.nanoTime()`
* Reads searchable text from an external `.txt` file
* Uses Java collections including `LinkedList`, `ArrayList`, and `HashMap`

## 🧠 Algorithms

### 1. Brute Force

The Brute Force algorithm checks the pattern against the text at every possible starting position.

**Time Complexity**

* Best case: `O(n)`
* Worst case: `O(n × m)`

where:

* `n` = length of the text
* `m` = length of the search pattern

### 2. Knuth–Morris–Pratt (KMP)

KMP improves searching by preprocessing the pattern and building a **failure function (LPS array)**. When a mismatch occurs, the algorithm uses previously matched information instead of restarting from the beginning of the pattern.

**Time Complexity**

* Preprocessing: `O(m)`
* Search: `O(n)`
* Overall: `O(n + m)`

### 3. Boyer–Moore

Boyer–Moore compares the pattern from right to left and uses a **bad-character table** to skip sections of the text after a mismatch.

This can significantly reduce the number of comparisons for many practical inputs.

## 📂 Project Structure

```text
Find-it-Fast-Java/
│
├── Search.java
├── prog1input1.txt
├── prog1input2 (1).txt
└── .gitattributes
```

### `Search.java`

Contains the complete implementation of:

* Brute Force search
* KMP search
* Boyer–Moore search
* Runtime measurement
* File input handling
* User input handling

### Input Files

The `.txt` files contain the text used to test and compare the search algorithms.

## 🚀 Getting Started

### Prerequisites

* Java Development Kit (JDK)
* Java compiler / IDE such as IntelliJ IDEA, Eclipse, or VS Code

### 1. Clone the repository

```bash
git clone https://github.com/sneha0832/Find-it-Fast-Java.git
cd Find-it-Fast-Java
```

### 2. Compile the program

```bash
javac Search.java
```

### 3. Run the program

```bash
java Search
```

The program will prompt:

```text
Enter the substring to search for:
```

Enter the substring you want to find.

## 💻 Example

```text
Enter the substring to search for:
algorithm

Brute Force took: 123456 ns
KMP took: 98765 ns
Boyer-Moore took: 87654 ns

Substring found at index: [42, 158, 301]
Substring found at index: [42, 158, 301]
Substring found at index: [42, 158, 301]
```

*Runtime values will vary depending on the machine and input size.*

## 📊 Performance Comparison

The project measures the execution time of each algorithm using Java's `System.nanoTime()`.

| Algorithm   | Main Technique                    | Worst-Case Search |
| ----------- | --------------------------------- | ----------------: |
| Brute Force | Character-by-character comparison |        `O(n × m)` |
| KMP         | Failure/LPS function              |        `O(n + m)` |
| Boyer–Moore | Bad-character heuristic           |        `O(n × m)` |

The practical performance of each algorithm depends on factors such as text size, pattern length, and character distribution.

## 🛠️ Technologies

* **Java**
* Java Collections Framework
* File I/O
* String Matching Algorithms
* Algorithm Analysis
* Runtime Benchmarking

## 🎯 What I Learned

This project provided hands-on experience with:

* Implementing and comparing fundamental string-search algorithms
* Understanding algorithmic time complexity
* Building the KMP failure function
* Implementing Boyer–Moore's bad-character heuristic
* Working with Java collections
* Reading and processing external files
* Measuring algorithm performance experimentally

## 👩‍💻 Author

**Sneha Patel**

[GitHub](https://github.com/sneha0832)

---

⭐ If you found this project useful, feel free to star the repository!
