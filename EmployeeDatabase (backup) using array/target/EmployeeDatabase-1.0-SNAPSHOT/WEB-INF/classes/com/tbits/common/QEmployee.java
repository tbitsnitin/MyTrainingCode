package com.tbits.common;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QEmployee extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<Employee> implements PersistableExpression<Employee>
{
    public static final QEmployee jdoCandidate = candidate("this");

    public static QEmployee candidate(String name)
    {
        return new QEmployee(null, name, 5);
    }

    public static QEmployee candidate()
    {
        return jdoCandidate;
    }

    public static QEmployee parameter(String name)
    {
        return new QEmployee(Employee.class, name, ExpressionType.PARAMETER);
    }

    public static QEmployee variable(String name)
    {
        return new QEmployee(Employee.class, name, ExpressionType.VARIABLE);
    }

    public final NumericExpression<Long> serialVersionUID;
    public final NumericExpression<Long> employee_Id;
    public final StringExpression employee_Name;
    public final StringExpression employee_Designation;
    public final NumericExpression<Double> employee_Salary;

    public QEmployee(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.serialVersionUID = new NumericExpressionImpl<Long>(this, "serialVersionUID");
        this.employee_Id = new NumericExpressionImpl<Long>(this, "employee_Id");
        this.employee_Name = new StringExpressionImpl(this, "employee_Name");
        this.employee_Designation = new StringExpressionImpl(this, "employee_Designation");
        this.employee_Salary = new NumericExpressionImpl<Double>(this, "employee_Salary");
    }

    public QEmployee(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.serialVersionUID = new NumericExpressionImpl<Long>(this, "serialVersionUID");
        this.employee_Id = new NumericExpressionImpl<Long>(this, "employee_Id");
        this.employee_Name = new StringExpressionImpl(this, "employee_Name");
        this.employee_Designation = new StringExpressionImpl(this, "employee_Designation");
        this.employee_Salary = new NumericExpressionImpl<Double>(this, "employee_Salary");
    }
}
