import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate, TextFormat, getPaginationState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './image-file.reducer';

export const ImageFile = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const imageFileList = useAppSelector(state => state.imageFile.entities);
  const loading = useAppSelector(state => state.imageFile.loading);
  const totalItems = useAppSelector(state => state.imageFile.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="image-file-heading" data-cy="ImageFileHeading">
        <Translate contentKey="jhipsterSampleApplication1App.imageFile.home.title">Image Files</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="jhipsterSampleApplication1App.imageFile.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/image-file/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jhipsterSampleApplication1App.imageFile.home.createLabel">Create new Image File</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {imageFileList && imageFileList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('code')}>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.code">Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('code')} />
                </th>
                <th className="hand" onClick={sort('title')}>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.title">Title</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('title')} />
                </th>
                <th className="hand" onClick={sort('alt')}>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.alt">Alt</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('alt')} />
                </th>
                <th className="hand" onClick={sort('filename')}>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.filename">Filename</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('filename')} />
                </th>
                <th className="hand" onClick={sort('data')}>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.data">Data</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('data')} />
                </th>
                <th className="hand" onClick={sort('createdDate')}>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.createdDate">Created Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdDate')} />
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.destination">Destination</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.tour">Tour</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.tourCategory">Tour Category</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.place">Place</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.placeCategory">Place Category</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.tourExtraCategory">Tour Extra Category</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.tourExtra">Tour Extra</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.vehicle">Vehicle</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplication1App.imageFile.driver">Driver</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {imageFileList.map((imageFile, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/image-file/${imageFile.id}`} color="link" size="sm">
                      {imageFile.id}
                    </Button>
                  </td>
                  <td>{imageFile.code}</td>
                  <td>{imageFile.title}</td>
                  <td>{imageFile.alt}</td>
                  <td>{imageFile.filename}</td>
                  <td>
                    {imageFile.data ? (
                      <div>
                        {imageFile.dataContentType ? (
                          <a onClick={openFile(imageFile.dataContentType, imageFile.data)}>
                            <img src={`data:${imageFile.dataContentType};base64,${imageFile.data}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {imageFile.dataContentType}, {byteSize(imageFile.data)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {imageFile.createdDate ? <TextFormat type="date" value={imageFile.createdDate} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{imageFile.createdBy ? imageFile.createdBy.login : ''}</td>
                  <td>
                    {imageFile.destination ? <Link to={`/destination/${imageFile.destination.id}`}>{imageFile.destination.code}</Link> : ''}
                  </td>
                  <td>{imageFile.tour ? <Link to={`/tour/${imageFile.tour.id}`}>{imageFile.tour.code}</Link> : ''}</td>
                  <td>
                    {imageFile.tourCategory ? (
                      <Link to={`/tour-category/${imageFile.tourCategory.id}`}>{imageFile.tourCategory.code}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{imageFile.place ? <Link to={`/place/${imageFile.place.id}`}>{imageFile.place.code}</Link> : ''}</td>
                  <td>
                    {imageFile.placeCategory ? (
                      <Link to={`/place-category/${imageFile.placeCategory.id}`}>{imageFile.placeCategory.code}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {imageFile.tourExtraCategory ? (
                      <Link to={`/tour-extra-category/${imageFile.tourExtraCategory.id}`}>{imageFile.tourExtraCategory.code}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{imageFile.tourExtra ? <Link to={`/tour-extra/${imageFile.tourExtra.id}`}>{imageFile.tourExtra.code}</Link> : ''}</td>
                  <td>{imageFile.vehicle ? <Link to={`/vehicle/${imageFile.vehicle.id}`}>{imageFile.vehicle.plate}</Link> : ''}</td>
                  <td>{imageFile.driver ? <Link to={`/driver/${imageFile.driver.id}`}>{imageFile.driver.name}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/image-file/${imageFile.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/image-file/${imageFile.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/image-file/${imageFile.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
                        }
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="jhipsterSampleApplication1App.imageFile.home.notFound">No Image Files found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={imageFileList && imageFileList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default ImageFile;
