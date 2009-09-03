// @(#)$Id: UnboundedStackRC2.java,v 1.4 2005/07/08 18:13:39 leavens Exp $

// Copyright (C) 1998, 1999 Iowa State University

// This file is part of JML

// JML is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2, or (at your option)
// any later version.

// JML is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with JML; see the file COPYING.  If not, write to
// the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.


package org.jmlspecs.samples.stacks;

//@ model import org.jmlspecs.models.*;

public abstract class UnboundedStackRC2 {

  /*@ public model JMLObjectSequence theStack;
    @ public initially theStack != null && theStack.isEmpty();
    @*/
  //@ public invariant theStack != null;

  /*@ public model_program {
    @   assume !theStack.isEmpty();
    @   theStack = theStack.trailer();
    @ }
    @*/
  public abstract void pop( );

  //@ public model_program { theStack = theStack.insertFront(x); }
  public abstract void push(Object x);

  /*@ public model_program {
    @   assume !theStack.isEmpty();
    @   return theStack.first();
    @ }
    @*/
  public abstract Object top( );
}
