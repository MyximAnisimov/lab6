package client.commands;

/**
 * Абстрактный класс, имеет поле с именем и описанием команды
 */
    public abstract class AbstractCommand implements Command{

        private final String name;
        private final String description;

        public AbstractCommand(String name, String description) {
            this.name = name;
            this.description = description;
        }
        @Override
        public String getName() {
            return name;
        }
        @Override
        public String getDescription() {
            return description;
        }
    }

