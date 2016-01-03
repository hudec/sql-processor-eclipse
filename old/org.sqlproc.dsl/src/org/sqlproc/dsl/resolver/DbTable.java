package org.sqlproc.dsl.resolver;

public class DbTable {

    private String name;
    private String type;
    private short ftype;
    private short ptype;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public short getFtype() {
        return ftype;
    }

    public void setFtype(short ftype) {
        this.ftype = ftype;
    }

    public short getPtype() {
        return ptype;
    }

    public void setPtype(short ptype) {
        this.ptype = ptype;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "DbTable [name=" + name + ", comment=" + comment + ", type=" + type + ", ftype=" + ftype + ", ptype="
                + ptype + "]";
    }
}
