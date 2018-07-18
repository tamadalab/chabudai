package jp.ac.kyoto_su.tamadalab.chabudai;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class VerbExtractionVisitor extends ASTVisitor{
    private MethodPool pool;
    private SimpleName className;

    public VerbExtractionVisitor(MethodPool pool){
        this.pool = pool;
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        this.className = node.getName();
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodDeclaration method){
        store(convert(method));
        return super.visit(method);
    }

    private Method convert(MethodDeclaration md){
        return new Method(className, md);
    }

    public void store(Method method){
        pool.store(method);
    }
}
