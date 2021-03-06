# -*- makefile -*- mode for emacs
# @(#) $Id: Makefile 1199 2009-02-17 19:42:32Z smshaner $

# Copyright (C) 2002 Iowa State University

# This file is part of JML

# JML is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2, or (at your option)
# any later version.

# JML is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.

# You should have received a copy of the GNU General Public License
# along with JML; see the file COPYING.  If not, write to
# the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.

TOPDIR = ../..

INFO_DIR = /usr/unsup/info
WWWMACHINE = monroe.cs.ucf.edu
WWW_DIR = /home/leavens/WWW/JML
FTPMACHINE = $(WWWMACHINE)
FTP_DIR = /home/leavens/WWW/JML/OldReleases
ANONFTPMACHINE = larch.cs.iastate.edu
ANONFTPDIR = /home/ftp/pub/techreports/TR98-06

SAMPLESDIR = $(TOPDIR)/org/jmlspecs/samples
THISDOWN = docs/prelimdesign

# Included JML files that are automatically generated
# from corresponding files in other directories

MODELS	       = ./models
MODEL_TEXINFOS = \
		$(MODELS)/JMLType.java.texinfo

STACKS	      =	./stacks
STACK_TEXINFOS = \
		$(STACKS)/BoundedStackException.java.texinfo \
		$(STACKS)/BoundedStackInterface.java.texinfo \
		$(STACKS)/BoundedStackInterface.jml.texinfo \
		$(STACKS)/BoundedStackInterface.refinesjava.texinfo \
		$(STACKS)/BoundedThing.java.texinfo \
		$(STACKS)/UnboundedStack.java.texinfo

DIGRAPH		= ./digraph
DIGRAPH_TEXINFOS = \
		$(DIGRAPH)/ArcType.jml.texinfo \
		$(DIGRAPH)/NodeType.java.texinfo \
		$(DIGRAPH)/Digraph.jml.texinfo

LOCAL		= ./local
LOCAL_TEXINFOS = \
		$(LOCAL)/IntMathOps.java.texinfo \
		$(LOCAL)/IntMathOps2.jmlrefined.texinfo \
		$(LOCAL)/IntMathOps2.java.texinfo \
		$(LOCAL)/IntMathOps3.java.texinfo \
		$(LOCAL)/IntMathOps4.java.texinfo \
		$(LOCAL)/Money.java.texinfo \
		$(LOCAL)/MoneyComparable.java.texinfo \
		$(LOCAL)/MoneyOps.java.texinfo \
		$(LOCAL)/MoneyAC.java.texinfo \
		$(LOCAL)/MoneyComparableAC.java.texinfo \
		$(LOCAL)/USMoney.java.texinfo \
		$(LOCAL)/Account.jml.texinfo \
		$(LOCAL)/Point2D.java.texinfo \
		$(LOCAL)/PlusAccount.jml.texinfo

TEXINFOS = $(MODEL_TEXINFOS) $(STACK_TEXINFOS) $(DIGRAPH_TEXINFOS) \
	   $(LOCAL_TEXINFOS)

# Implicit Rules (for GNU Make)

$(MODELS)/%.java.texinfo : $(SAMPLESDIR)/../models/%.java
	@$(CODE2TEXILGPL) $< > $@

$(STACKS)/%.java.texinfo : $(SAMPLESDIR)/stacks/%.java
	@$(CODE2TEXI) $< > $@

$(STACKS)/%.javarefined.texinfo : $(SAMPLESDIR)/stacks/%.java-refined
	@$(CODE2TEXI) $< > $@

$(STACKS)/%.jml.texinfo : $(SAMPLESDIR)/stacks/%.jml
	@$(CODE2TEXI) $< > $@

$(STACKS)/%.refinesjava.texinfo : $(SAMPLESDIR)/stacks/%.refines-java
	@$(CODE2TEXI) $< > $@

$(DIGRAPH)/%.java.texinfo : $(SAMPLESDIR)/digraph/%.java
	@$(CODE2TEXI) $< > $@

$(DIGRAPH)/%.jml.texinfo : $(SAMPLESDIR)/digraph/%.jml
	@$(CODE2TEXI) $< > $@

$(LOCAL)/%.java.texinfo : $(SAMPLESDIR)/prelimdesign/%.java
	@$(CODE2TEXI) $< > $@

$(LOCAL)/%.jml.texinfo : $(SAMPLESDIR)/prelimdesign/%.jml
	@$(CODE2TEXI) $< > $@

$(LOCAL)/%.javarefined.texinfo : $(SAMPLESDIR)/prelimdesign/%.java-refined
	@$(CODE2TEXI) $< > $@

$(LOCAL)/%.jmlrefined.texinfo : $(SAMPLESDIR)/prelimdesign/%.jml-refined
	@$(CODE2TEXI) $< > $@


# Target Definitions.
#
TEXIBASE = prelimdesign

all : html pdf $(TEXIBASE).ps.gz

postscript : $(TEXIBASE).ps

$(TEXIBASE).ps  : $(TEXIBASE).dvi

dvi: $(TEXIBASE).dvi

$(TEXIBASE).dvi : $(TEXIBASE).texinfo $(TEXINFOS)
	rm -f $(TEXIBASE).aux
	$(TEXI2DVI) $(TEXIBASE).texinfo

pdf: $(TEXIBASE).pdf

$(TEXIBASE).pdf : $(TEXIBASE).texinfo $(TEXINFOS)
	$(TEXI2PDF) $(TEXIBASE).texinfo

$(TEXIBASE).dvi.gz : $(TEXIBASE).dvi
	gzip -f --best $(TEXIBASE).dvi

$(TEXIBASE).ps.gz : $(TEXIBASE).ps
	gzip -f --best $(TEXIBASE).ps

info : $(TEXIBASE).info

$(TEXIBASE).info : $(TEXIBASE).texinfo $(TEXINFOS)
	$(MAKEINFO) $(TEXIBASE).texinfo

texinfos: $(TEXINFOS)


html: $(TEXIBASE)/$(TEXIBASE)_toc.html

$(TEXIBASE)/$(TEXIBASE)_toc.html: $(TEXIBASE).texinfo $(TEXINFOS) \
		adjust-html-file Makefile
	@$(DELTREE) $(TEXIBASE)
	@$(MKDIR) $(TEXIBASE)
	@$(TEXI2HTML) $(TEXI2HTMLOPTS) $(TEXIBASE).texinfo
	@if test '!' -f $(TEXIBASE)_toc.html ; \
	then mv $(TEXIBASE)/*.html . ; \
	fi
	@for f in *.html ; \
	do \
		$(PERL) -p adjust-html-file $$f > $(TEXIBASE)/$$f ; \
	done
	@-$(CP) $(TEXIBASE)/$(TEXIBASE)_toc.html $(TEXIBASE)/index.html
	@$(RM) $(TEXIBASE)*.html
	@$(MAKE) cleansamplecopies

publish: ftp-docs www

ftp-docs: docs-for-ftp just-ftp-docs

docs-for-ftp: $(TEXIBASE).ps.gz $(TEXIBASE).pdf html

just-ftp-docs:
	ssh $(FTPMACHINE) $(RM) $(FTP_DIR)/$(TEXIBASE).ps.gz
	ssh $(FTPMACHINE) $(RM) $(FTP_DIR)/$(TEXIBASE).pdf
	scp -pq $(TEXIBASE).ps.gz $(TEXIBASE).pdf $(FTPMACHINE):$(FTP_DIR)
	ssh $(FTPMACHINE) chmod 644 $(FTP_DIR)/$(TEXIBASE).ps.gz $(FTP_DIR)/$(TEXIBASE).pdf

anon-ftp-update:
	scp -pq $(TEXIBASE).ps.gz $(ANONFTPMACHINE):$(ANONFTPDIR)/TR.ps.gz
	scp -pq $(TEXIBASE).pdf $(ANONFTPMACHINE):$(ANONFTPDIR)/TR.pdf
	scp -pq TR.abs README $(ANONFTPMACHINE):$(ANONFTPDIR)

www: html
	chmod u+w $(TEXIBASE) $(TEXIBASE)/*.html
	chmod a+r $(TEXIBASE)/*.html
	chmod a+rx $(TEXIBASE)
	ssh $(WWWMACHINE) $(DELTREE) $(WWW_DIR)/$(TEXIBASE)
	scp -prq $(TEXIBASE) $(WWWMACHINE):$(WWW_DIR)/

# Clean up stuff
#
clean-this: cleanlocal

cleanlocal:
	@touch junk.log junk.blg junk.foo-ckd
	$(RM) *.log *.blg *.*-ckd core
	$(RM) $(TEXINFOS)
	$(RM) $(TEXIBASE).aux $(TEXIBASE).cp $(TEXIBASE).cps \
		$(TEXIBASE).fn $(TEXIBASE).ky $(TEXIBASE).log \
		$(TEXIBASE).pg $(TEXIBASE).pgs $(TEXIBASE).toc \
		$(TEXIBASE).tp $(TEXIBASE).vr

clean-classes-this: remove-dot-class-files

distclean-this: distclean-local

distclean-local: clean-this cleanhtmldir cleansamplecopies

cleansamplecopies:
	@$(RM) $(MODELS)/*.texinfo
	@$(RM) $(LOCAL)/*.texinfo
	@$(RM) $(STACKS)/*.texinfo
	@$(RM) $(DIGRAPH)/*.texinfo

cleanhtmldir:
	$(DELTREE) $(TEXIBASE)

# include standard project targets

include $(TOPDIR)/Make.ProjDefs
