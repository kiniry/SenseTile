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

BIBDIR=../../bibliography
LATEXDIR=../../latex

${paper}.pdf:	${paper}.tex
	export BIBINPUTS="$(BIBDIR)//:"; \
	export BSTINPUTS="$(LATEXDIR)//:"; \
	export TEXINPUTS="$(LATEXDIR)//:"; \
	rubber --pdf ${paper}

view:	${paper}.pdf
	open -a Skim.app ${paper}.pdf

cleanup:
	rm -f _region_* *.aux *.log *# *~

clean:
	rubber --pdf --clean ${paper}
