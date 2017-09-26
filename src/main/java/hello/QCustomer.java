package hello;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = -1554478694L;

    public static final QCustomer customer = new QCustomer("customer");

    public final ListPath<Address, QAddress> addresses = this.<Address, QAddress>createList("addresses", Address.class, QAddress.class, PathInits.DIRECT2);

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastName = createString("lastName");

    public QCustomer(String variable) {
        super(Customer.class, forVariable(variable));
    }

    public QCustomer(Path<? extends Customer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomer(PathMetadata metadata) {
        super(Customer.class, metadata);
    }

}

