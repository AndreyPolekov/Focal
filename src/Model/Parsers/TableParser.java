package Model.Parsers;

import Model.Data.Table.Table;

public abstract class TableParser extends Parser {
    protected Table table;

    public void setTable(Table table) {
        this.table = table;
    }
    public abstract void parse();
    public abstract String getTournamentUrl();
}
