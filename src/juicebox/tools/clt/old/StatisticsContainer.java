/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2011-2020 Broad Institute, Aiden Lab, Rice University, Baylor College of Medicine
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package juicebox.tools.clt.old;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class StatisticsContainer {
    //Variables for calculating statistics
    public final List<Map<Integer, Long>> hindIII = new ArrayList<>();
    public final List<Map<Integer, Long>> mapQ = new ArrayList<>();
    public final List<Map<Integer, Long>> mapQInter = new ArrayList<>();
    public final List<Map<Integer, Long>> mapQIntra = new ArrayList<>();
    public final List<Map<Integer, Long>> innerM = new ArrayList<>();
    public final List<Map<Integer, Long>> outerM = new ArrayList<>();
    public final List<Map<Integer, Long>> rightM = new ArrayList<>();
    public final List<Map<Integer, Long>> leftM = new ArrayList<>();
    
    public long unique = 0;
    public long[] intraFragment = new long[2];
    public long[] threePrimeEnd = new long[2];
    public long[] fivePrimeEnd = new long[2];
    public long[] dangling = new long[2];
    public long[] ligation = new long[2];
    public long[] inner = new long[2];
    public long[] outer = new long[2];
    public long[] left = new long[2];
    public long[] right = new long[2];
    public long[] intra = new long[2];
    public long[] inter = new long[2];
    public long[] small = new long[2];
    public long[] large = new long[2];
    public long[] verySmall = new long[2];
    public long[] verySmallDangling = new long[2];
    public long[] smallDangling = new long[2];
    public long[] largeDangling = new long[2];
    public long[] interDangling = new long[2];
    public long[] trueDanglingIntraSmall = new long[2];
    public long[] trueDanglingIntraLarge = new long[2];
    public long[] trueDanglingInter = new long[2];
    public long[] totalCurrent = new long[2];
    public long[] underMapQ = new long[2];
    
    public long[] oneKBRes = new long[2];
    public long[] twoKBRes = new long[2];
    public long[] fiveKBRes = new long[2];
    public long[] oneKBResDangling = new long[2];
    public long[] twoKBResDangling = new long[2];
    public long[] fiveKBResDangling = new long[2];
    
    private static final long[] bins = {10, 12, 15, 19, 23, 28, 35, 43, 53, 66, 81, 100, 123, 152, 187, 231, 285, 351, 433, 534, 658, 811, 1000, 1233, 1520, 1874, 2310, 2848, 3511, 4329, 5337, 6579, 8111, 10000, 12328, 15199, 18738, 23101, 28480, 35112, 43288, 53367, 65793, 81113, 100000, 123285, 151991, 187382, 231013, 284804, 351119, 432876, 533670, 657933, 811131, 1000000, 1232847, 1519911, 1873817, 2310130, 2848036, 3511192, 4328761, 5336699, 6579332, 8111308, 10000000, 12328467, 15199111, 18738174, 23101297, 28480359, 35111917, 43287613, 53366992, 65793322, 81113083, 100000000, 123284674, 151991108, 187381742, 231012970, 284803587, 351119173, 432876128, 533669923, 657933225, 811130831, 1000000000, 1232846739, 1519911083, 1873817423, 2310129700L, 2848035868L, 3511191734L, 4328761281L, 5336699231L, 6579332247L, 8111308308L, 10000000000L};
    
    public StatisticsContainer() {
        for (int i = 0; i < 2; i++) {
            hindIII.add(new HashMap<>());
            mapQ.add(new HashMap<>());
            mapQInter.add(new HashMap<>());
            mapQIntra.add(new HashMap<>());
            innerM.add(new HashMap<>());
            outerM.add(new HashMap<>());
            rightM.add(new HashMap<>());
            leftM.add(new HashMap<>());
        }
    }
    
    public void add(StatisticsContainer individualContainer, int numberOfMapQValues) {
        unique += individualContainer.unique;

        for (int j=0; j<numberOfMapQValues; j++) {
            for (int i = 1; i <= 2000; i++) {
                hindIII.get(j).put(i, hindIII.get(j).getOrDefault(i, 0L) + individualContainer.hindIII.get(j).getOrDefault(i, 0L));
            }
            for (int i = 1; i <= 200; i++) {
                mapQ.get(j).put(i, mapQ.get(j).getOrDefault(i, 0L) + individualContainer.mapQ.get(j).getOrDefault(i, 0L));
                mapQInter.get(j).put(i, mapQInter.get(j).getOrDefault(i, 0L) + individualContainer.mapQInter.get(j).getOrDefault(i, 0L));
                mapQIntra.get(j).put(i, mapQIntra.get(j).getOrDefault(i, 0L) + individualContainer.mapQIntra.get(j).getOrDefault(i, 0L));
            }
            for (int i = 1; i <= 100; i++) {
                innerM.get(j).put(i, innerM.get(j).getOrDefault(i, 0L) + individualContainer.innerM.get(j).getOrDefault(i, 0L));
                outerM.get(j).put(i, outerM.get(j).getOrDefault(i, 0L) + individualContainer.outerM.get(j).getOrDefault(i, 0L));
                rightM.get(j).put(i, rightM.get(j).getOrDefault(i, 0L) + individualContainer.rightM.get(j).getOrDefault(i, 0L));
                leftM.get(j).put(i, leftM.get(j).getOrDefault(i, 0L) + individualContainer.leftM.get(j).getOrDefault(i, 0L));
            }
        }

        for(int i=0;i<numberOfMapQValues;i++) {
            intraFragment[i] += individualContainer.intraFragment[i];
            threePrimeEnd[i] += individualContainer.threePrimeEnd[i];
            fivePrimeEnd[i] += individualContainer.fivePrimeEnd[i];
            dangling[i] += individualContainer.dangling[i];
            ligation[i] += individualContainer.ligation[i];
            inner[i] += individualContainer.inner[i];
            outer[i] += individualContainer.outer[i];
            left[i] += individualContainer.left[i];
            right[i] += individualContainer.right[i];
            intra[i] += individualContainer.intra[i];
            inter[i] += individualContainer.inter[i];
            large[i] += individualContainer.large[i];
            small[i] += individualContainer.small[i];
            fiveKBRes[i] += individualContainer.fiveKBRes[i];
            twoKBRes[i] += individualContainer.twoKBRes[i];
            oneKBRes[i] += individualContainer.oneKBRes[i];
            verySmall[i] += individualContainer.verySmall[i];
            verySmallDangling[i] += individualContainer.verySmallDangling[i];
            oneKBResDangling[i]+= individualContainer.oneKBResDangling[i];
            twoKBResDangling[i]+= individualContainer.twoKBResDangling[i];
            fiveKBResDangling[i]+= individualContainer.fiveKBResDangling[i];
            smallDangling[i] += individualContainer.smallDangling[i];
            largeDangling[i] += individualContainer.largeDangling[i];
            interDangling[i] += individualContainer.interDangling[i];
            trueDanglingIntraSmall[i] += individualContainer.trueDanglingIntraSmall[i];
            trueDanglingIntraLarge[i] += individualContainer.trueDanglingIntraLarge[i];
            trueDanglingInter[i] += individualContainer.trueDanglingInter[i];
            totalCurrent[i] += individualContainer.totalCurrent[i];
            underMapQ[i] += individualContainer.underMapQ[i];
        }
    }
    
    private String commify(long value) {
        return NumberFormat.getNumberInstance(Locale.US).format(value);
    }
    
    private String percentify(long num, long total) {
        return String.format("%.2f", num * 100f / total) + "%";
    }
    
    private String wholePercentify(long num, long total) {
        return String.format("%.0f", num * 100f / total) + "%";
    }
    
    public void outputStatsFile(List<String> statsFiles) {
        for (int i = 0; i < statsFiles.size(); i++) {
            boolean sequencedReadsGiven = false;
            long reads = 1L;
            File statFile = new File(statsFiles.get(i));
            //output statistics file for first mapq calculation
            if (statFile.exists()) {
                try {
                    BufferedReader stats = new BufferedReader(new FileReader(statFile));
                    String statsData = stats.readLine();
                    while (statsData != null) {
                        if (statsData.contains("Sequenced")) {
                            sequencedReadsGiven = true;
                            String[] tokens = statsData.split(":");
                            reads = Long.parseLong(tokens[1].replaceAll("[, ]", ""));
                        }
                        statsData = stats.readLine();
                    }
                    stats.close();
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }
            if (statFile.exists()) {
                try {
                    BufferedWriter statsOut = new BufferedWriter(new FileWriter(statFile, true));
                    if (unique == 0) {
                        unique++;
                    }
                    if(sequencedReadsGiven) {
                        statsOut.write("Intra-fragment Reads: " + commify(intraFragment[i]) + " (" + percentify(intraFragment[i],reads) + " / " + percentify(intraFragment[i],unique) + ")\n");
                        statsOut.write("Below MAPQ Threshold: " + commify(underMapQ[i]) + " (" + percentify(underMapQ[i],reads) + " / " + percentify(underMapQ[i],unique)  + ")\n");
                        statsOut.write("Hi-C Contacts: " + commify(totalCurrent[i]) + " (" + percentify(totalCurrent[i],reads) + " / " + percentify(totalCurrent[i],unique)  + ")\n");
                        statsOut.write(" Ligation Motif Present: " + commify(ligation[i]) + " (" + percentify(ligation[i],reads) + " / " + percentify(ligation[i],unique)  + ")\n");
                        if ((fivePrimeEnd[i] + threePrimeEnd[i]) > 0) {
                            statsOut.write(" 3' Bias (Long Range): " + wholePercentify(threePrimeEnd[i],threePrimeEnd[i]+fivePrimeEnd[i]));
                            statsOut.write(" - " + wholePercentify(fivePrimeEnd[i],threePrimeEnd[i]+fivePrimeEnd[i]) + "\n");
                        } else {
                            statsOut.write(" 3' Bias (Long Range): 0\\% \\- 0\\%\n");
                        }
                        if (large[i] > 0) {
                            statsOut.write(" Pair Type %(L-I-O-R): " + wholePercentify(left[i],large[i]));
                            statsOut.write(" - " + wholePercentify(inner[i],large[i]));
                            statsOut.write(" - " + wholePercentify(outer[i],large[i]));
                            statsOut.write(" - " + wholePercentify(right[i],large[i])+ "\n");
                        } else {
                            statsOut.write(" Pair Type %(L-I-O-R): 0\\% - 0\\% - 0\\% - 0\\%\n");
                        }
                        statsOut.write("Inter-chromosomal: " + commify(inter[i]) + " (" + percentify(inter[i],reads) + " / " + percentify(inter[i],unique)  + ")\n");
                        statsOut.write("Intra-chromosomal: " + commify(intra[i]) + " (" + percentify(intra[i],reads) + " / " + percentify(intra[i],unique)  + ")\n");
                        statsOut.write("Short Range (<20Kb): \n");
                        statsOut.write("  <10B: " + commify(verySmall[i]) + " (" + percentify(verySmall[i],reads) + " / " + percentify(verySmall[i],unique)  + ")\n");
                        statsOut.write("  10B-1kB: " + commify(oneKBRes[i]) + " (" + percentify(oneKBRes[i],reads) + " / " + percentify(oneKBRes[i],unique)  + ")\n");
                        statsOut.write("  1kB-2kB: " + commify(twoKBRes[i]) + " (" + percentify(twoKBRes[i],reads) + " / " + percentify(twoKBRes[i],unique)  + ")\n");
                        statsOut.write("  2kB-5kB: " + commify(fiveKBRes[i]) + " (" + percentify(fiveKBRes[i],reads) + " / " + percentify(fiveKBRes[i],unique)  + ")\n");
                        statsOut.write("  5kB-20kB: " + commify(small[i]) + " (" + percentify(small[i],reads) + " / " + percentify(small[i],unique)  + ")\n");
                        statsOut.write("Long Range (>20Kb): " + commify(large[i]) + " (" + percentify(large[i],reads) + " / " + percentify(large[i],unique)  + ")\n");
                    }
                    else{
                        statsOut.write("Intra-fragment Reads: " + commify(intraFragment[i]) + " ((" + percentify(intraFragment[i],unique) + ")\n");
                        statsOut.write("Below MAPQ Threshold: " + commify(underMapQ[i]) + " ((" + percentify(underMapQ[i],unique) + ")\n");
                        statsOut.write("Hi-C Contacts: " + commify(totalCurrent[i]) + " ((" + percentify(totalCurrent[i],unique) + ")\n");
                        statsOut.write(" Ligation Motif Present: " + commify(ligation[i]) + " ((" + percentify(ligation[i],unique) + ")\n");
                        if ((fivePrimeEnd[i] + threePrimeEnd[i]) > 0) {
                            statsOut.write(" 3' Bias (Long Range): " + wholePercentify(threePrimeEnd[i],threePrimeEnd[i]+fivePrimeEnd[i]));
                            statsOut.write(" - " + wholePercentify(fivePrimeEnd[i],threePrimeEnd[i]+fivePrimeEnd[i]) + "\n");
                        } else {
                            statsOut.write(" 3' Bias (Long Range): 0\\% \\- 0\\%\n");
                        }
                        if (large[i] > 0) {
                            statsOut.write(" Pair Type %(L-I-O-R): " + wholePercentify(left[i],large[i]));
                            statsOut.write(" - " + wholePercentify(inner[i],large[i]));
                            statsOut.write(" - " + wholePercentify(outer[i],large[i]));
                            statsOut.write(" - " + wholePercentify(right[i],large[i])+ "\n");
                        } else {
                            statsOut.write(" Pair Type %(L-I-O-R): 0\\% - 0\\% - 0\\% - 0\\%\n");
                        }
                        statsOut.write("Inter-chromosomal: " + commify(inter[i]) + " ((" + percentify(inter[i],unique) + ")\n");
                        statsOut.write("Intra-chromosomal: " + commify(intra[i]) + " ((" + percentify(intra[i],unique) + ")\n");
                        statsOut.write("Short Range (<20Kb): \n");
                        statsOut.write("  <10B: " + commify(verySmall[i]) + " ((" + percentify(verySmall[i],unique) + ")\n");
                        statsOut.write("  10B-1kB: " + commify(oneKBRes[i]) + " ((" + percentify(oneKBRes[i],unique) + ")\n");
                        statsOut.write("  1kB-2kB: " + commify(twoKBRes[i]) + " ((" + percentify(twoKBRes[i],unique) + ")\n");
                        statsOut.write("  2kB-5kB: " + commify(fiveKBRes[i]) + " ((" + percentify(fiveKBRes[i],unique) + ")\n");
                        statsOut.write("  5kB-20kB: " + commify(small[i]) + " ((" + percentify(small[i],unique) + ")\n");
                        statsOut.write("Long Range (>20Kb): " + commify(large[i]) + " ((" + percentify(large[i],unique) + ")\n");
                    }

                    statsOut.close();
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }
        }
    }

    public void writeHistFile(List<String> statsFiles) {
        //write for mapq if file exists
        for (int j = 0; j < statsFiles.size(); j++) {
            if (new File(statsFiles.get(j)).exists()) {
                //separate stats file name
                int index = statsFiles.get(j).lastIndexOf("\\");
                String statsFilePath = statsFiles.get(j).substring(0, index + 1); //directories
                String statsFileName = statsFiles.get(j).substring(index + 1).replaceAll(".txt", ""); //filename
                String histsFile = statsFilePath + statsFileName + "_hists.m";
                try {
                    BufferedWriter hist = new BufferedWriter(new FileWriter(histsFile, false));
                    hist.write("A = [\n");
                    for (int i = 1; i <= 2000; i++) {
                        long tmp = hindIII.get(j).getOrDefault(i, 0L);
                        hist.write(tmp + " ");
                    }
                    hist.write("\n];\n");

                    hist.write("B = [\n");
                    for (int i = 1; i <= 200; i++) {
                        long tmp = mapQ.get(j).getOrDefault(i, 0L);
                        long tmp2 = mapQIntra.get(j).getOrDefault(i, 0L);
                        long tmp3 = mapQInter.get(j).getOrDefault(i, 0L);
                        hist.write(tmp + " " + tmp2 + " " + tmp3 + "\n");
                    }
                    hist.write("\n];\n");

                    hist.write("D = [\n");
                    for (int i = 0; i < bins.length; i++) {
                        long tmp = innerM.get(j).getOrDefault(i, 0L);
                        long tmp2 = outerM.get(j).getOrDefault(i, 0L);
                        long tmp3 = rightM.get(j).getOrDefault(i, 0L);
                        long tmp4 = leftM.get(j).getOrDefault(i, 0L);
                        hist.write(tmp + " " + tmp2 + " " + tmp3 + " " + tmp4 + "\n");
                    }
                    hist.write("\n];");

                    hist.write("x = [\n");
                    for (long bin : bins) {
                        hist.write(bin + " ");
                    }
                    hist.write("\n];\n");
                    hist.close();
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }
        }
    }
}
