#
# $Id: Makefile 2795 2007-09-23 19:35:53Z dmz $
#

# include ../Makefile.defs

paper =		paper

talk = 		talk

texfiles =	$(wildcard *.tex)

dvifiles =	$(foreach texfile,$(texfiles),\
		$(subst .tex,.dvi,$(texfile)))

psfiles =	$(foreach texfile,$(texfiles),\
		$(subst .tex,.ps,$(texfile)))

pdffiles =	$(foreach texfile,$(texfiles),\
		$(subst .tex,.pdf,$(texfile)))

.PHONY: all talk archive

all:	${paper}.pdf

talk: 	${talk}.ps ${talk}.pdf

# archive: ime.pdf
# 	tar cjf ENTCS-UITP-04.tbz \
# 		entcsmacro.sty snapshot.sty kt.tex \
# 		Makefile \
# 		ime.tex pvsui.bib ime.pdf ime.bbl \
# 		ECB.pdf function_hierarchy.pdf pvs.pdf

PREFIX=/Users/kiniry/Sandboxes/svn
LATEXDIR=$(PREFIX)/latex
# export BIBINPUTS="~/Sandboxes/Writing/Bibliography//:"
# export BSTINPUTS="$(LATEXDIR)//:"
# export TEXINPUTS="$(LATEXDIR)//:"

${paper}.pdf:	${paper}.tex
	export BIBINPUTS="../../bibliography//:"; \
	export BSTINPUTS="$(LATEXDIR)//:"; \
	export TEXINPUTS="$(LATEXDIR)//:"; \
	rubber --pdf ${paper}

view:	${paper}.pdf
	open -a Skim.app ${paper}.pdf

cleanup:
	rm -f _region_* *.aux *.log *# *~

clean:
	rubber --pdf --clean ${paper}
