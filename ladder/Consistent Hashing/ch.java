import java.util.*;

public class ch {
    /*
     * @param n: a positive integer
     * @return: n x 3 matrix
     */

    public List<List<Integer>> consistentHashing(int n) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        List<Integer> machine = new ArrayList<Integer>();

        machine.add(0);
        machine.add(359);
        machine.add(1);

        results.add(machine);

        //Find biggest gap between machines
        for (int i = 1; i < n; ++i) {
            List<Integer> new_machine = new ArrayList<Integer>();

            int index = 0;

            for (int j = 1; j < i; ++j) {
                if (results.get(j).get(1) - results.get(j).get(0) + 1 > results.get(index).get(1) - results.get(index).get(0) + 1)
                    index = j;
            }

            // Gets the start and end partition of the biggest gap
            int x = results.get(index).get(0);
            int y = results.get(index).get(1);

            // reduce the partition of the existing machine
            results.get(index).set(1, (x + y) / 2);

            // create a new machine
            new_machine.add((x + y) / 2 + 1);
            new_machine.add(y);
            new_machine.add(i + 1);
            results.add(new_machine);
        }
        return results;
    }
}