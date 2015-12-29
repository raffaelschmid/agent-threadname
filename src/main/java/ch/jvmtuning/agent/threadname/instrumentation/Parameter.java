package ch.jvmtuning.agent.threadname.instrumentation;

public class Parameter{
        private final int position;
        private final String name;

        public Parameter(int position, String name) {
            this.position = position;
            this.name = name;
        }

        public int getPosition() {
            return position;
        }

        public String getName() {
            return name;
        }
    }