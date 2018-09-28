
TUTORIAL

Searching for repetitive patterns in biological sequences is a major problem in bioinformatics research. Repetitive substrings occur to a striking extent in an organism genome, especially in higher-order organisms such as eukaryotes. For example indicates that families of repeated sequences account for about one third of the human genome. Although the role of these repetitive patterns is still mainly unknown, some have been linked to several inherited diseases, and are thought to play a role in major events of evolution . A related concern in the repetitive structures is based on the notion of maximality. A maximal repeat in a sequence S is a substring that occurs at least twice in S, and that cannot be further extended to the left and/or right without destroying it being a repeat. For example, S = AACGTCGACGTTAACGTC is a DNA sequence which includes two maximal repeats: ACGT, which occurs three times (starting at positions 1, 7, and 13, shown in boldface), and AACGTC, which occurs twice (at positions 0 and 12, shown with gray background). Some applications may consider other types of repetitive structures. For instance, a supermaximal repeat is a maximal repeat that never occurs as a substring of any other maximal repeat. In our example, AACGTC is a supermaximal repeat, while ACGT is not, since it occurs as a substring of AACGTC.
How to use:

Here, an tool can be excuted for searching maximal and supermaximal reepats in a DNA sequecne

REQUIREMENTS--

The requirements for executing an algorithm are:

Operating System: Microsoft Windows/Unix

Compiler: JDK 1.7 or above
INSTALL

To implement algorithm on Windows OS, download the file (from Downloads) and run it to implement the algorithm.
Steps for excution

The algorithm can be implemented from source code:

1. Download and unzip source code package (Download "SourceCode.zip" from Downloads).

2. Go to Java Compiler and then run the source code named "Parser.java" for searching maximal and super maximal repeats

3. Give the name of input file in the source code and their path

4. The algorithm will be list of all maximal and super maximal repeats along with their frequencies and locations