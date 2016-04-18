package cc.devfun.struct.compiler.model;

import java.util.Collection;

public interface Commentable {
    void addComment(String comment);
    boolean haveComments();
    Collection<String> getComments();
    String getHtmlComments();
}
